package org.jcv.reservation.model;


import jakarta.persistence.*;
import lombok.Data;
import org.jcv.common.PaxType;

import java.util.Date;

@Entity
@Data
public class Traveller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private int paxNo;
    private String firstName;
    private String lastName;
    private PaxType type; // ADT, CHD, INF
    private String gender;
    private Date dateOfBirth;
    private String nationality;
    private String passportNo;
    private Date passportExpiry;

}
