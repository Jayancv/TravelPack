package org.jcv.common.cart;

import lombok.Data;
import org.jcv.common.BookingStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private String cartId;
    List<CartItemDto> cartItems = new ArrayList<>();
    private Double totalPrice;
    private BookingStatus bookingStatus;

    private List<TravellerDto> travellers; // Booking level travellers
}
