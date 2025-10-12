package org.jcv.cart.client;

import org.jcv.common.result.dto.TourResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "search-service", url = "${SEARCH_SERVICE_URL}")
public interface SearchServiceClient {

    @GetMapping("/tour")
    List<TourResultDto> getTourResults(@RequestParam() Integer adult,
                                      @RequestParam() Integer child,
                                      @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                      @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                      @RequestParam() Integer duration,
                                      @RequestParam() String durationType,
                                      @RequestParam(required = false) List<String> tourCodes,
                                      @RequestParam(required = false) String tourName,
                                      @RequestParam(required = false) String city,
                                      @RequestParam(required = false) String tourType,
                                      @RequestParam(required = false) Boolean groupTour);
    @GetMapping("/tour/detail")
    public TourResultDto getDetailTourProduct(@RequestParam() Integer adult,
                                    @RequestParam() Integer child,
                                    @RequestParam() LocalDate fromDate,
                                    @RequestParam() LocalDate toDate,
                                    @RequestParam() Integer duration,
                                    @RequestParam() String durationType,
                                    @RequestParam() String tourCode,
                                    @RequestParam(required = false) String tourName,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String tourType,
                                    @RequestParam(required = false) Boolean groupTour) ;

}
