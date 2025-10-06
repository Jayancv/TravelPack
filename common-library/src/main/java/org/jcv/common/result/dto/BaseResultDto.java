package org.jcv.common.result.dto;

import lombok.Data;
import org.jcv.common.ProductType;

import java.time.LocalDate;

@Data
public abstract class BaseResultDto {
    private int adult;
    private int child;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String countryCode;
    private String cityCode;

    private String supplierName;
    private String supplierCode;

    private ProductType type;
}
