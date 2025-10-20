package org.jcv.reservation.mapper;

import org.jcv.common.ProductType;
import org.jcv.common.cart.CartItemDto;
import org.jcv.common.reservation.dto.BookingItemDto;
import org.jcv.reservation.model.BookingItem;

public interface IBookingItemMapper<T extends BookingItem, V extends CartItemDto> {

    boolean supports(ProductType type);

    BookingItemDto mapToBookingItemDto(T bookingItem);

    BookingItem mapToBookingItem(V cartItem);

}