package org.jcv.search.model.data;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractCost {

    private Double  unitCost = 0.0;
    private Double  adultCost = 0.0;
    private Double  teenCost = 0.0;
    private Double  childCost = 0.0;
    private Double  infantCost = 0.0;

    private String currency;
}
