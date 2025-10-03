package org.jcv.cart.service;

import org.jcv.cart.client.ProductServiceClient;
import org.jcv.cart.dto.CartDto;
import org.jcv.cart.dto.CartItemDto;
import org.jcv.cart.dto.ProductDto;
import org.jcv.cart.model.Cart;
import org.jcv.cart.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CartService {

    private final RedisTemplate<String, Cart> redisTemplate;

    private static final String CART_KEY_PREFIX = "cart:";

    @Autowired
    private ProductServiceClient productServiceClient;

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

    public Optional<Cart> getCart(long cartId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(CART_KEY_PREFIX + cartId));
    }

    public CartDto addItem(long cartId, CartItemDto itemDto) {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = getCart(cartId);
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
        CartItem item = modelMapper.map(itemDto, CartItem.class);
        // TODO get price from product service
        ProductDto productDto = productServiceClient.getProductById(item.getProductId());
        item.setPrice(productDto.getPrice());
        cart.addItem(item);
        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    public CartDto removeItem(long cartId, String productId) {
        Cart cart = null;
        if (cartId > -1) {
            Optional<Cart> exCart = getCart(cartId);
            if (exCart.isPresent()) {
                cart = exCart.get();
            }
        }
        if (cart == null) {
            return null;
        }
        cart.removeItem(Integer.valueOf(productId));
        saveCart(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    public void saveCart(Cart cart) {
        redisTemplate.opsForValue().set(CART_KEY_PREFIX + cart.getCartId(), cart, 1, TimeUnit.DAYS);
    }

    public void deleteCart(long cartId) {
        redisTemplate.delete(CART_KEY_PREFIX + cartId);
    }
}
