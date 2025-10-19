package org.jcv.common.cart;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.ProductType;
import org.jcv.common.constant.ApiSplitter;
import org.jcv.common.result.dto.HotelResultDto;
import org.jcv.common.result.dto.TourResultDto;
import org.jcv.common.supplier.dto.SupplierDto;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,          // use logical name
        include = JsonTypeInfo.As.PROPERTY,  // include in JSON as a field
        property = "productType",                    // JSON field name
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TourItemDto.class, name = "TOU")
})
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

    private SupplierDto supplier;

    private int quantity;
    private double price;
    private double cost;

    public String getItemKey() {
        return productType.getCode() + ApiSplitter.ITEM_KEY_SPLITTER + itemNo;
    }
}
