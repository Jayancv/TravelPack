package org.jcv.cart.model;

import lombok.Data;
import org.jcv.cart.model.supplier.Supplier;
import org.jcv.common.ProductType;

import java.util.Date;

@Data
public abstract class Contract {

    private Long contractId;
    private String contractName;
    private String contractCode;

    private Date validFrom;
    private Date validTo;
    private boolean salesEnabled;

    private ProductType type;

    private Supplier supplier;

    private String cityCode;
    private String countryCode;

    public abstract ProductType getProductType();
}
