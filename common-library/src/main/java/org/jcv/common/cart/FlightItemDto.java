package org.jcv.common.cart;

import lombok.Data;

@Data
public class FlightItemDto extends CartItemDto {
    private String departure;
    private String arrival;

}
