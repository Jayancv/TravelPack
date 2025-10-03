package org.jcv.cart.controller;

import org.jcv.cart.dto.CartDto;
import org.jcv.cart.dto.CartItemDto;
import org.jcv.cart.model.Cart;
import org.jcv.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart-service")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * Creating empty cart
     *
     * @return Cart
     */
    @PostMapping("/cart")
    public CartDto createCart() {
        CartDto newCart = cartService.createCart();
        return newCart;
    }

    /**
     * Cart read
     *
     * @param cartId
     * @return Cart
     */
    @GetMapping("/cart/{cartId}")
    public Optional<Cart> getCartByCartId(@PathVariable long cartId) {
        return cartService.getCart(cartId);
    }

    @PostMapping("/cart/{cartId}/item")
    public CartDto addItemToCart(@PathVariable long cartId, @RequestBody CartItemDto itemDto) {
        return cartService.addItem(cartId, itemDto);
    }

    @DeleteMapping("/cart/{cartId}/item/{productId}")
    public CartDto removeItemFromCart(@PathVariable long cartId , @PathVariable String productId){
        return cartService.removeItem(cartId, productId);
    }

    @DeleteMapping("/cart/{cartId}")
    public void deleteCart(@PathVariable long cartId ){
        cartService.deleteCart(cartId);
    }

}
