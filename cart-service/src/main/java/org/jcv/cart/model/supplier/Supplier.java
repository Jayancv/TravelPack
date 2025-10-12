package org.jcv.cart.model.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {

    private Long id;
    private String code;
    private String name;

    private String address;
    private String cityCode;
    private String countryCode;


}
