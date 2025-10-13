package org.jcv.product.model.tour;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcv.product.model.Contract;
import org.jcv.common.ProductType;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TourContract extends Contract {

    private String tourName;
    private String tourCode;

    private String tourType;
    private boolean groupTour;
    private int duration;

    @ElementCollection
    @CollectionTable(
            name = "tour_itinerary",
            joinColumns = @JoinColumn(name = "tour_contract_id")
    )
    private List<Itinerary> itineraries;

    @Override
    public ProductType getProductType() {
        return ProductType.TOU;
    }
}
