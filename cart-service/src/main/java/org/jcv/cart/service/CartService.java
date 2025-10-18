package org.jcv.cart.service;

import org.jcv.cart.client.ProductServiceClient;
import org.jcv.cart.client.SearchServiceClient;
import org.jcv.cart.dto.CartDto;
import org.jcv.cart.mapper.IProductResultMapper;
import org.jcv.common.ProductType;
import org.jcv.cart.model.Cart;
import org.jcv.cart.model.CartItem;
import org.jcv.common.result.dto.BaseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
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

    public CartDto addItem(long cartId, BaseResultDto itemDto) {
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
