package org.jcv.common.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.DurationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourBookingDto extends BookingItemDto{
    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;
}
