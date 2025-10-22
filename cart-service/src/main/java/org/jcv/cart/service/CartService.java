package org.jcv.cart.service;

import org.jcv.cart.client.ProductServiceClient;
import org.jcv.cart.client.SearchServiceClient;
import org.jcv.cart.eventpublisher.KafkaEventPublisher;
import org.jcv.cart.model.Traveller;
import org.jcv.cart.util.TravellerUtils;
import org.jcv.common.BookingStatus;
import org.jcv.common.PaxType;
import org.jcv.common.cart.CartDto;
import org.jcv.cart.mapper.IProductResultMapper;
import org.jcv.common.ProductType;
import org.jcv.cart.model.Cart;
import org.jcv.cart.model.CartItem;
import org.jcv.common.cart.CartResultDto;
import org.jcv.common.cart.TravellerDto;
import org.jcv.common.event.cart.CartCheckOutEvent;
import org.jcv.common.result.dto.BaseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CartService {

    private final RedisTemplate<String, Cart> redisTemplate;

    private static final String CART_KEY_PREFIX = "cart:";

    @Autowired
    private List<IProductResultMapper<? extends BaseResultDto>> mappers;

    @Autowired
    private List<IProductSearchService<? extends BaseResultDto>> productSearchServices;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private SearchServiceClient searchServiceClient;

    @Autowired
    private org.modelmapper.ModelMapper modelMapper;

    @Autowired
    private KafkaEventPublisher eventPublisher;

    public CartService(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public CartDto createCart() {
        Cart cart = createEmptyCart();
        return modelMapper.map(cart, CartDto.class);
    }

    private Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setCartId(CartIdGenerator.generateId());
        saveCart(cart);
        return cart;
    }

    public Optional<CartDto> getCart(long cartId) {
        Optional<Cart> cartOpt = loadCart(cartId);

        if (cartOpt.isPresent()) {
            return Optional.ofNullable(modelMapper.map(cartOpt.get(), CartDto.class));
        }
        return Optional.empty();
    }

    private Optional<Cart> loadCart(long cartId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(CART_KEY_PREFIX + cartId));

    }

    public CartDto addItem(long cartId, CartResultDto cartResultDto) {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = loadCart(cartId);
            if (exCart.isPresent()) {
                cart = exCart.get();
            }
        } else {
            cart = createEmptyCart();
        }
        if (cart == null) {
            return null;
            // TODO pass correct exception
        }
        BaseResultDto itemDto = cartResultDto.getResultDto();
        List<Long> itemPaxs = cartResultDto.getTravellerIds();

        int maxItemNumber = cart.getCartItems().stream()
                .filter(i -> i.getProductType().getCode().equals(itemDto.getProductType()))
                .mapToInt(CartItem::getItemNo)
                .max()
                .orElse(0);

        ProductType type = ProductType.fromCode(itemDto.getProductType());
        IProductResultMapper<BaseResultDto> mapper = findMapper(type);

        CartItem item = mapper.mapToCartItem(itemDto);
        item.setItemNo(maxItemNumber + 1);

        IProductSearchService<BaseResultDto> searchService = findSearchService(type);
        BaseResultDto result = searchService.searchProduct(item);
        mapper.updateCartItemWithSearchResult(item, result);

        cart.addItem(item);

        // Create dummy travellers if not already present
        if (cart.getTravellers().isEmpty()) {
            List<Traveller> dummyTravellers = TravellerUtils.generateDummyTravellers(item.getAdult(), 0, item.getChild(), 0, 1);
            item.setTravellers(dummyTravellers);
            cart.setTravellers(dummyTravellers);
        } else {
            List<Traveller> allTravellers = cart.getTravellers();
            int[] paxCounts = new int[4]; //[ADT,TEEN,CHD,IFN]
            for (long n : itemPaxs) {
                if (n > 0) {
                    Optional<Traveller> tOpt = allTravellers.stream().filter(a -> a.getPaxNo() == n).findFirst();
                    if (tOpt.isPresent()) {
                        switch (tOpt.get().getType()) {
                            case ADULT -> paxCounts[0] = paxCounts[0] + 1;
                            case TEEN -> paxCounts[1] = paxCounts[1] + 1;
                            case CHILD -> paxCounts[2] = paxCounts[2] + 1;
                            case INFANT -> paxCounts[3] = paxCounts[3] + 1;
                        }
                        item.getTravellerIds().add((long) tOpt.get().getPaxNo());
                    }
                }
            }
            int maxNum = allTravellers.size();
            List<Traveller> newTravellers = TravellerUtils.generateDummyTravellers(
                    itemDto.getAdult() - paxCounts[0], 0,
                    itemDto.getChild() - paxCounts[2], 0, maxNum + 1);
            if (!newTravellers.isEmpty()) {
                for (Traveller t : newTravellers) {
                    item.getTravellerIds().add((long) t.getPaxNo());
                    cart.getTravellers().add(t);
                }

            }

        }

        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }


    public CartDto removeItem(long cartId, String itemKey) {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = loadCart(cartId);
            if (exCart.isPresent()) {
                cart = exCart.get();
            }
        }
        if (cart == null) {
            return null;
        }
//        ProductType productType = CartItem.extractProductType(itemKey);
//        int itemNo = CartItem.extractItemNumber(itemKey);
        cart.removeItem(itemKey);
        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    public void saveCart(Cart cart) {
        redisTemplate.opsForValue().set(CART_KEY_PREFIX + cart.getCartId(), cart, 1, TimeUnit.DAYS);
    }

    public void deleteCart(long cartId) {
        redisTemplate.delete(CART_KEY_PREFIX + cartId);
    }


    public CartDto confirmCart(long cartId) throws Exception {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = loadCart(cartId);
            if (exCart.isPresent()) {
                cart = exCart.get();
            }
        }
        if (cart == null) {
            throw new Exception("Cart not found");
            // TODO pass correct exception
        }

        CartCheckOutEvent event = CartCheckOutEvent.builder()
                .cartId(cart.getCartId())
                .userId(-1L)
                .checkedOutAt(LocalDateTime.now())
                .cart(modelMapper.map(cart, CartDto.class))
                .build();

        // Publish event
        eventPublisher.publish("booking-checkout", event);

        // Update cart status
        cart.setBookingStatus(BookingStatus.QUOTE);
        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }


    public CartDto updateTraveller(long cartId, List<TravellerDto> travellerDtos) {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = loadCart(cartId);
            if (exCart.isPresent()) {
                cart = exCart.get();
            }
        }
        if (cart == null) {
            return null;
            // TODO pass correct exception
        }

        for (TravellerDto dto : travellerDtos) {
            Traveller updated = modelMapper.map(dto, Traveller.class);
            cart.getTravellers().stream()
                    .filter(existing -> Objects.equals(existing.getId(), updated.getId()))
                    .findFirst()
                    .ifPresent(existing -> {
                        // Update fields (but keep same object reference)
                        existing.setFirstName(updated.getFirstName());
                        existing.setLastName(updated.getLastName());
                        existing.setType(updated.getType());
                        existing.setGender(updated.getGender());
                        existing.setDateOfBirth(updated.getDateOfBirth());
                        existing.setNationality(updated.getNationality());
                        existing.setPassportNo(updated.getPassportNo());
                        existing.setPassportExpiry(updated.getPassportExpiry());
                    });
        }

        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }


    @SuppressWarnings("unchecked")
    private IProductResultMapper<BaseResultDto> findMapper(ProductType type) {
        return (IProductResultMapper<BaseResultDto>) mappers.stream()
                .filter(m -> m.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported product type: " + type));
    }

    @SuppressWarnings("unchecked")
    private <T extends BaseResultDto> IProductSearchService<T> findSearchService(ProductType type) {
        return (IProductSearchService<T>) productSearchServices.stream()
                .filter(s -> s.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No search service for type " + type));
    }

}
