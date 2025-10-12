package org.jcv.cart.dto;

import lombok.Data;
import org.jcv.cart.model.supplier.Supplier;
import org.jcv.common.ProductType;
import org.jcv.common.constant.ApiSplitter;

import java.util.Date;

@Data
public class CartItemDto {

    private int itemNo;
    private ProductType productType;
    private String itemKey;

    private int adult;
    private int child;
    private Date fromDate;
    private Date toDate;

    private String countryCode;
    private String cityCode;

    private Supplier supplier;

    private int quantity;
    private double price;

    public String getItemKey() {
        return productType.getCode() + ApiSplitter.ITEM_KEY_SPLITTER + itemNo;
    }
}
