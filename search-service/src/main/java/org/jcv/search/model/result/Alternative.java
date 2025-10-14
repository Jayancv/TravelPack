package org.jcv.search.model.result;

import lombok.Data;

@Data
public class Alternative extends CostPrice{
    private String altCode;
    private String altName;
}
