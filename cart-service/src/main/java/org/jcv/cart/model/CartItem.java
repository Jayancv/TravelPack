package org.jcv.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.cart.model.supplier.Supplier;
import org.jcv.common.ItemBookingStatus;
import org.jcv.common.ItemStatus;
import org.jcv.common.ProductType;
import org.jcv.common.constant.ApiSplitter;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CartItem implements Serializable {

    private int itemNo;
    private ProductType productType;
    private String itemKey;

    private ItemStatus itemStatus;
    private ItemBookingStatus itemBookingStatus;

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

    public static ProductType extractProductType(String itemKey) {
        String[] parts = itemKey.split(ApiSplitter.ITEM_KEY_SPLITTER);
        return ProductType.fromCode(parts[0]);
    }

    public static int extractItemNumber(String itemKey) {
        String[] parts = itemKey.split(ApiSplitter.ITEM_KEY_SPLITTER);
        return Integer.parseInt(parts[1]);
    }
}
