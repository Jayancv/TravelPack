package org.jcv.common.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEventDto {
    private long bookingId;
    private String message;
    private String status;
}
