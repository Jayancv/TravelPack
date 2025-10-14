package org.jcv.common.result.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
public abstract class BaseResultDto extends CostPriceDto {
    private int adult;
    private int child;
    private LocalDate fromDate;
    private LocalDate toDate;

    private String countryCode;
    private String cityCode;

    private String supplierName;
    private String supplierCode;

    private String productType;

    private double price;
    private double cost;


    private List<AlternativeDto> alternatives;

    private AlternativeDto selectedAlternative;

}
