package org.jcv.search.model.data;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.ProductType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourContract extends Contract {

    private String tourName;
    private String tourCode;

    private String tourType;
    private boolean groupTour;
    private int duration;

    @Override
    public ProductType getProductType() {
        return ProductType.TOU;
    }
}
