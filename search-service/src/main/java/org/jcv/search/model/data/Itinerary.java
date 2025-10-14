package org.jcv.search.model.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Itinerary extends ContractCost {

    private String itineraryCode;
    private String itineraryName;
}
