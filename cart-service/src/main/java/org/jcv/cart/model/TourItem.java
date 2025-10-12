package org.jcv.cart.model;

import lombok.Data;
import org.jcv.common.DurationType;

@Data
public class TourItem extends CartItem {
    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;
}
