package org.jcv.search.model.criteria;

import lombok.Data;
import org.jcv.common.DurationType;
import org.jcv.common.ProductType;

import java.util.List;

@Data
public class HotelSearchCriteria extends BaseSearchCriteria {
    private String city;
    private String country;
    private int duration;
    private DurationType durationType;
    private String tourType;
    private boolean groupTour;

    private String htlName;
    private List<String> htlCodes;

    @Override
    ProductType geProductType() {
        return ProductType.HTL;
    }
}
