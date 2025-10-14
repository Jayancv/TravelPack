package org.jcv.common.result.dto;

import lombok.Data;

@Data
public class AlternativeDto extends CostPriceDto {
    private String altCode;
    private String altName;
}
