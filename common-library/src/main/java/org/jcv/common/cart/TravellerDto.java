package org.jcv.common.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.PaxType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravellerDto {
    private Long id;
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
