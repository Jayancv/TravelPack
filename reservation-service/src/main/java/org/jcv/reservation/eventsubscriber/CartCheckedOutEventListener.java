package org.jcv.reservation.eventsubscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jcv.common.event.cart.CartCheckOutEvent;
import org.jcv.reservation.service.ReservationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartCheckedOutEventListener {


    private final ReservationService reservationService;
    private final ObjectMapper mapper;

    public CartCheckedOutEventListener(ReservationService reservationService, ObjectMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "booking-checkout", groupId = "reservation-service-group")
    public void consume(String message) {
        try {
            CartCheckOutEvent event = mapper.readValue(message, CartCheckOutEvent.class);
            reservationService.createReservation(event);
        } catch (Exception e) {
            System.out.println("Failed to process event: " + message);
            e.printStackTrace();
        }
    }
}
