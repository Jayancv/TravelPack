package org.jcv.cart.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private String cartId;
    List<CartItemDto> cartItems = new ArrayList<>();
    private Double totalPrice;
}
