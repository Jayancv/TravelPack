package org.jcv.cart.controller;

import org.jcv.common.cart.CartDto;
import org.jcv.cart.service.CartService;
import org.jcv.common.cart.TravellerDto;
import org.jcv.common.result.dto.BaseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<CartDto> createCart() {
        CartDto newCart = cartService.createCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }

    /**
     * Cart read
     *
     * @param cartId
     * @return Cart
     */
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartDto> getCartByCartId(@PathVariable long cartId) {
        Optional<CartDto> cart = cartService.getCart(cartId);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cart/{cartId}/item")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable long cartId, @RequestBody BaseResultDto itemDto) {
        CartDto updatedCart = cartService.addItem(cartId, itemDto);
        if (updatedCart == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/cart/{cartId}/item/{itemKey}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable long cartId, @PathVariable String itemKey) {
        CartDto updatedCart = cartService.removeItem(cartId, itemKey);
        if (updatedCart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/cart/{cartId}")
    public void deleteCart(@PathVariable long cartId) {
        cartService.deleteCart(cartId);
    }

    @PostMapping("/cart/{cartId}/checkout")
    public ResponseEntity<CartDto> cartCheckout(@PathVariable long cartId) {
        CartDto updatedCart = null;
        try {
            updatedCart = cartService.confirmCart(cartId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (updatedCart == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/cart/{cartId}/traveller")
    public ResponseEntity<CartDto> updateTraveller(@PathVariable long cartId,
                                                   @RequestBody List<TravellerDto> updatedTravellers) {
        CartDto updatedCart = null;
        try {
            updatedCart = cartService.updateTraveller(cartId, updatedTravellers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (updatedCart == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedCart);
    }

}
