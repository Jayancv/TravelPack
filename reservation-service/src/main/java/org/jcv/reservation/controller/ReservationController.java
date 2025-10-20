package org.jcv.reservation.controller;

import org.jcv.common.reservation.dto.BookingDto;
import org.jcv.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/reservation-service/booking")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;



    @GetMapping("/{bookingId}")
    public BookingDto getContractById(@PathVariable Long bookingId) {
        return reservationService.getBooking(bookingId);
    }


}
