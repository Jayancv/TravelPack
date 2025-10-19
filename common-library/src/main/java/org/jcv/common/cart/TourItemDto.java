package org.jcv.common.cart;

import lombok.Data;
import org.jcv.common.DurationType;

@Data
public class TourItemDto extends CartItemDto {
    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;
}
