package org.jcv.common.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.BookingStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private long bookingId;
    private List<BookingItemDto> bookingItems;
    private BookingStatus bookingStatus;
}
