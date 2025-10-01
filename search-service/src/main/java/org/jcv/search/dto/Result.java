package org.jcv.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result
{
    private int id;
    private int productId;
    private String productCode;
    private String productName;

}
