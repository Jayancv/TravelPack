package org.jcv.reservation.model;

import jakarta.persistence.*;
import lombok.Data;
import org.jcv.common.BookingStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq_gen")
    @SequenceGenerator(name = "booking_id_seq_gen", sequenceName = "booking_id_seq", allocationSize = 1)
    private long bookingId;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingItem> bookingItems;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Traveller> travellers = new ArrayList<>();


    public void addItem(BookingItem item) {
        item.setBooking(this);
        if (bookingItems == null) {
            bookingItems = new ArrayList<>();
        }
        bookingItems.add(item);
    }
}
