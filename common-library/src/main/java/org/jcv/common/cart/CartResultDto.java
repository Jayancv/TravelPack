package org.jcv.common.cart;

import lombok.Data;
import org.jcv.common.result.dto.BaseResultDto;

import java.util.List;

@Data
public class CartResultDto {
    private BaseResultDto resultDto;
    private List<Long> travellerIds;
}
