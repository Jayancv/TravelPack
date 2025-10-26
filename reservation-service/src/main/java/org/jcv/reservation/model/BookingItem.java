package org.jcv.reservation.model;


import jakarta.persistence.*;
import lombok.*;
import org.jcv.common.ItemBookingStatus;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BookingItem {

    @EmbeddedId
    private BookingItemId id;

    @MapsId("bookingId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
//    private String productType;
//    private int itemNo;

    private Date fromDate;
    private Date toDate;

    @Enumerated(EnumType.STRING)
    private ItemBookingStatus itemBookingStatus;

    private long supplierId;

    private String currency;
    private double price;
    private double cost;

    private List<Long> travellerIds;

}
