package org.jcv.common.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItineraryDto extends ContractCostDto {

    private String itineraryCode;
    private String itineraryName;
}
