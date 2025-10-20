package org.jcv.reservation.service;


import org.jcv.common.event.cart.CartCheckOutEvent;
import org.jcv.common.reservation.dto.BookingDto;
import org.jcv.reservation.mapper.BookingMapper;
import org.jcv.reservation.model.Booking;
import org.jcv.reservation.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private ModelMapper modelMapper;

    public Booking createReservation(CartCheckOutEvent event) {
        Booking bkg = bookingMapper.mapToBooking(event.getCart());

        bookingRepository.save(bkg);
        return bkg;
    }

    public BookingDto getBooking(Long bookingId) {

        BookingDto bookingDto = bookingMapper.mapToBookingDto(bookingRepository.getReferenceById(bookingId));
        return bookingDto;
    }


}
