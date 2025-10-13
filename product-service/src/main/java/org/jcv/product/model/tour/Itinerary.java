package org.jcv.product.model.tour;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.product.model.ContractCost;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Itinerary extends ContractCost {

    private String itineraryCode;
    private String itineraryName;
}
