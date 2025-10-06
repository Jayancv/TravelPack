package org.jcv.search.model.criteria;

import lombok.Data;
import org.jcv.common.DurationType;
import org.jcv.common.ProductType;

import java.util.List;

@Data
public class TourSearchCriteria extends BaseSearchCriteria {

    private String destinationCity;
    private String destinationCountry;
    private int duration;
    private DurationType durationType;
    private String tourType;
    private boolean groupTour;

    private String tourName;
    private List<String> tourCodes;

    @Override
    ProductType geProductType() {
        return ProductType.TOU;
    }

}
