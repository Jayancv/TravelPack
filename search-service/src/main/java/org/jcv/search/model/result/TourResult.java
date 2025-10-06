package org.jcv.search.model.result;

import lombok.Data;
import org.jcv.common.DurationType;
import org.jcv.common.ProductType;

@Data
public class TourResult extends BaseResult{

    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;


    public TourResult(){
        super.setType(ProductType.TOU);
    }
}
