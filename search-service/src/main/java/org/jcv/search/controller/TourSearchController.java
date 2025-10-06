package org.jcv.search.controller;

import org.jcv.common.DurationType;
import org.jcv.common.result.dto.TourResultDto;
import org.jcv.search.model.criteria.TourSearchCriteria;
import org.jcv.search.service.TourSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/search-service/tour")
public class TourSearchController {

    @Autowired
    private TourSearchService searchService;

    @GetMapping("")
    public List<TourResultDto> getProducts(@RequestParam() Integer adult,
                                           @RequestParam() Integer child,
                                           @RequestParam() LocalDate fromDate,
                                           @RequestParam() LocalDate toDate,
                                           @RequestParam() Integer duration,
                                           @RequestParam() String durationType,
                                           @RequestParam(required = false) List<String> tourCodes,
                                           @RequestParam(required = false) String tourName,
                                           @RequestParam(required = false) String city,
                                           @RequestParam(required = false) String tourType,
                                           @RequestParam(required = false) Boolean groupTour) {
        TourSearchCriteria searchCriteria = new TourSearchCriteria();
        searchCriteria.setAdult(adult);
        searchCriteria.setChild(child);
        searchCriteria.setFromDate(fromDate);
        searchCriteria.setToDate(toDate);
        searchCriteria.setTourName(tourName);
        searchCriteria.setTourCodes(tourCodes);
        searchCriteria.setDestinationCity(city);
        searchCriteria.setTourType(tourType);
        searchCriteria.setDuration(duration);
        searchCriteria.setDurationType(DurationType.valueOf(durationType));
        searchCriteria.setGroupTour(groupTour);

        return searchService.summarySearch(searchCriteria);
    }
}
