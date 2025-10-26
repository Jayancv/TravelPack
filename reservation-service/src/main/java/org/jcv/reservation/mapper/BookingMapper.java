package org.jcv.reservation.mapper;


import org.jcv.common.ProductType;
import org.jcv.common.cart.CartDto;
import org.jcv.common.cart.CartItemDto;
import org.jcv.common.cart.TravellerDto;
import org.jcv.common.reservation.dto.BookingDto;
import org.jcv.common.reservation.dto.BookingItemDto;
import org.jcv.reservation.model.Booking;
import org.jcv.reservation.model.BookingItem;
import org.jcv.reservation.model.Traveller;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookingMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private List<IBookingItemMapper<? extends BookingItem, ? extends CartItemDto>> mappers;

    public BookingDto mapToBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setBookingItems(mapToBookingItemDtos(booking.getBookingItems()));
        bookingDto.setTravellers(mapToTravellerDtos(booking.getTravellers()));
        return bookingDto;
    }

    private List<BookingItemDto> mapToBookingItemDtos(List<BookingItem> bookingItems) {
        List<BookingItemDto> dtoList = new ArrayList<>();
        for (BookingItem bkgItem : bookingItems) {
            dtoList.add(mapToBookingItemDto(bkgItem));
        }
        return dtoList;
    }

    private List<TravellerDto> mapToTravellerDtos(List<Traveller> travellers) {
        return modelMapper.map(travellers, new TypeToken<List<TravellerDto>>() {
        }.getType());
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
        List<Traveller> travellers = mapToTravellers(cartDto.getTravellers());
        travellers.forEach(traveller -> traveller.setBooking(booking));
        booking.setTravellers(travellers);

        return booking;
    }

    private List<BookingItem> mapToBookingItems(List<CartItemDto> cartItemDtos) {
        List<BookingItem> dtoList = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            dtoList.add(mapToBookingItem(cartItemDto));
        }
        return dtoList;
    }

    private List<Traveller> mapToTravellers(List<TravellerDto> travellerDtos) {
        if (travellerDtos == null || travellerDtos.isEmpty()) {
            return new ArrayList<>();
        }
        return travellerDtos.stream()
                .map(dto -> {
                    Traveller traveller = modelMapper.map(dto, Traveller.class);
                    traveller.setId(null); // ensure Hibernate treats it as new
                    return traveller;
                })
                .collect(Collectors.toList());
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
