package org.jcv.reservation.mapper;

import org.jcv.common.ProductType;
import org.jcv.common.cart.TourItemDto;
import org.jcv.common.reservation.dto.BookingItemDto;
import org.jcv.common.reservation.dto.TourBookingDto;
import org.jcv.reservation.model.BookingItem;
import org.jcv.reservation.model.BookingItemId;
import org.jcv.reservation.model.TourBookingItem;
import org.springframework.stereotype.Component;


@Component
public class TourBookingItemMapper implements IBookingItemMapper<TourBookingItem, TourItemDto> {

    @Override
    public boolean supports(ProductType type) {
        return type == ProductType.TOU;
    }

    @Override
    public BookingItemDto mapToBookingItemDto(TourBookingItem item) {
        TourBookingDto tourBookingDto = new TourBookingDto();
        tourBookingDto.setItemNo(item.getId().getItemNo() );
        tourBookingDto.setProductType(item.getId().getProductType() );
        tourBookingDto.setPrice(item.getPrice());
        tourBookingDto.setCost(item.getCost());
        tourBookingDto.setCurrency(item.getCurrency());

        // TODO map other fields

        return tourBookingDto;
    }

    @Override
    public BookingItem mapToBookingItem(TourItemDto cartItem) {
        TourBookingItem bookingItem = new TourBookingItem();
        bookingItem.setId(new BookingItemId(-1L, cartItem.getProductType().getCode(), cartItem.getItemNo()));
        bookingItem.setPrice(cartItem.getPrice());
        bookingItem.setCost(cartItem.getCost());

        // TODO map other fields

        return bookingItem;
    }


}
