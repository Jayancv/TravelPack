package org.jcv.search.model.result;

import lombok.Data;
import org.jcv.common.ProductType;

import java.time.LocalDate;
import java.util.List;

@Data
public class BaseResult extends CostPrice {

    private int adult;
    private int child;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String countryCode;
    private String cityCode;

    private String supplierName;
    private String supplierCode;

    private ProductType productType;

    private List<Alternative> alternatives;

    private Alternative selectedAlternative;

    public void setSelectedAlternative(Alternative alternative) {
        this.selectedAlternative = alternative;
        this.add(alternative);
    }

}
