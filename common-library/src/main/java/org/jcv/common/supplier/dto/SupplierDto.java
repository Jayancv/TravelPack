package org.jcv.common.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private Long id;
    private String code;
    private String name;

    private String address;
    private String cityCode;
    private String countryCode;
}
