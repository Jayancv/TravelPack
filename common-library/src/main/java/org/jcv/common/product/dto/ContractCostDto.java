package org.jcv.common.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractCostDto {

    private long id;

    private double unitCost;
    private double adultCost;
    private double teenCost;
    private double childCost;
    private double infantCost;

    private String currency;

}
