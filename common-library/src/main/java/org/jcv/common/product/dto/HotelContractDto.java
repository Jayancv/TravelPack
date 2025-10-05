package org.jcv.common.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelContractDto extends ContractDto {
    private String hotelName;
    private String hotelCode;
}
