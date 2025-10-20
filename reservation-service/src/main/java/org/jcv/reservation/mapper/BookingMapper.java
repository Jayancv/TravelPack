package org.jcv.reservation.mapper;


import org.jcv.common.ProductType;
import org.jcv.common.cart.CartDto;
import org.jcv.common.cart.CartItemDto;
import org.jcv.common.reservation.dto.BookingDto;
import org.jcv.common.reservation.dto.BookingItemDto;
import org.jcv.reservation.model.Booking;
import org.jcv.reservation.model.BookingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookingMapper {


    @Autowired
    private List<IBookingItemMapper<? extends BookingItem, ? extends CartItemDto>> mappers;

    public BookingDto mapToBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setBookingItems(mapToBookingItemDtos(booking.getBookingItems()));
        return bookingDto;
    }

    private List<BookingItemDto> mapToBookingItemDtos(List<BookingItem> bookingItems) {
        List<BookingItemDto> dtoList = new ArrayList<>();
        for (BookingItem bkgItem : bookingItems) {
            dtoList.add(mapToBookingItemDto(bkgItem));
        }
        return dtoList;
    }

    private BookingItemDto mapToBookingItemDto(BookingItem bkgItem) {

        IBookingItemMapper<BookingItem, CartItemDto> mapper = findMapper(ProductType.valueOf(bkgItem.getId().getProductType()));
        BookingItemDto item = mapper.mapToBookingItemDto(bkgItem);
        return item;
    }

    public Booking mapToBooking(CartDto cartDto) {
        Booking booking = new Booking();
        List<BookingItem> bkgItemList = mapToBookingItems(cartDto.getCartItems());
        for (BookingItem bkgItem : bkgItemList) {
            booking.addItem(bkgItem);
        }
        return booking;
    }

    private List<BookingItem> mapToBookingItems(List<CartItemDto> cartItemDtos) {
        List<BookingItem> dtoList = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            dtoList.add(mapToBookingItem(cartItemDto));
        }
        return dtoList;
    }

    private BookingItem mapToBookingItem(CartItemDto cartItemDto) {

        IBookingItemMapper<BookingItem, CartItemDto> mapper = findMapper(cartItemDto.getProductType());
        BookingItem item = mapper.mapToBookingItem(cartItemDto);
        return item;
    }

    private IBookingItemMapper<BookingItem, CartItemDto> findMapper(ProductType type) {
        return (IBookingItemMapper<BookingItem, CartItemDto>) mappers.stream()
                .filter(m -> m.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported product type: " + type));
    }


}
