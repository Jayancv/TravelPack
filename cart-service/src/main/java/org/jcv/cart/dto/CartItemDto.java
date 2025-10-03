package org.jcv.cart.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private int productId;
    private String productCode;
    private String productName;
    private int quantity;
    private double price;
}
