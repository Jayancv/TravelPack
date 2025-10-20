package org.jcv.common.cart;

import lombok.Data;
import org.jcv.common.DurationType;

@Data
public class HotelItemDto extends CartItemDto {
    private String hotelName;
    private String hotelCode;
    private DurationType durationType;
    private int duration;
}
