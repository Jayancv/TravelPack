package org.jcv.common.result.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.ProductType;

import java.time.LocalDate;

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
        @JsonSubTypes.Type(value = TourResultDto.class, name = "TOU"),
        @JsonSubTypes.Type(value = HotelResultDto.class, name = "HTL")
})
public abstract class BaseResultDto {
    private int adult;
    private int child;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String countryCode;
    private String cityCode;

    private String supplierName;
    private String supplierCode;

    private String productType;
}
