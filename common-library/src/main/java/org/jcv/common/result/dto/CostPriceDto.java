package org.jcv.common.result.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostPriceDto {
    private double price;
    private double cost;

    private Double adultCost;
    private Double teenCost;
    private Double childCost;
    private Double infantCost;

    private Double adultPrice;
    private Double teenPrice;
    private Double childPrice;
    private Double infantPrice;

}
