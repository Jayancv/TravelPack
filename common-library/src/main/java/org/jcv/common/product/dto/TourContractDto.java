package org.jcv.common.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourContractDto extends ContractDto {
    private String tourName;
    private String tourCode;

    private String tourType;
    private boolean groupTour;
    private int duration;
}
