package org.jcv.common.result.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.common.DurationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourResultDto extends BaseResultDto {

    private String tourName;
    private String tourCode;
    private String tourType;
    private DurationType durationType;
    private int duration;


}
