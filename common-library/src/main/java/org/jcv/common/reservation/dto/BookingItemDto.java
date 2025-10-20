package org.jcv.common.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.ItemBookingStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BookingItemDto {
    private String productType;
    private int itemNo;

    private Date fromDate;
    private Date toDate;

    private ItemBookingStatus itemBookingStatus;

    private long supplierId;

    private String currency;
    private double price;
    private double cost;

}
