package org.jcv.common.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.ProductType;
import org.jcv.common.supplier.dto.SupplierDto;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ContractDto {

    private String contractId;
    private String contractName;
    private String contractCode;

    private Date validFrom;
    private Date validTo;
    private boolean salesEnabled;


    private ProductType type;

    private SupplierDto supplier;

    private String cityCode;
    private String countryCode;
}
