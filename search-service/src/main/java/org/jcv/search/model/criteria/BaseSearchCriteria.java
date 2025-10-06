package org.jcv.search.model.criteria;

import lombok.Data;
import org.jcv.common.ProductType;

import java.time.LocalDate;
import java.util.List;

@Data
public abstract class BaseSearchCriteria {

    // Pax counts
    private int adult;
    private int child;

    private LocalDate fromDate;
    private LocalDate toDate;
    private List<String> supplierCodes;

    private ProductType type;

    abstract ProductType geProductType();

}
