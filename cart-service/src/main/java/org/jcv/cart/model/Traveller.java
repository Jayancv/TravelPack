package org.jcv.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.PaxType;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traveller  implements Serializable {
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
