package org.jcv.common.result.dto;

import lombok.Data;
import org.jcv.common.DurationType;

@Data
public class TourResultDto extends BaseResultDto {

    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;


}
