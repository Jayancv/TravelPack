package org.jcv.reservation.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcv.common.DurationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TourBookingItem extends BookingItem{

    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;

}
