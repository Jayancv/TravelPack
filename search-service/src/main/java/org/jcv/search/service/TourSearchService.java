package org.jcv.search.service;

import org.jcv.common.DurationType;
import org.jcv.common.ProductType;
import org.jcv.common.result.dto.TourResultDto;
import org.jcv.search.mapper.TourResultMapper;
import org.jcv.search.model.criteria.TourSearchCriteria;
import org.jcv.search.model.data.TourContract;
import org.jcv.search.model.result.TourResult;
import org.jcv.search.repository.TourProductRepository;
import org.jcv.search.specification.TourSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TourSearchService implements ISearchService<TourSearchCriteria, TourResultDto> {

    @Autowired
    private TourProductRepository tourProductRepository;

    @Autowired
    private TourResultMapper mapper;

    @Override
    public List<TourResultDto> summarySearch(TourSearchCriteria criteria) {

        Specification<TourContract> spec = TourSpecification.fromCriteria(criteria);
        List<TourContract> tourContracts = tourProductRepository.findAll(spec);
        List<TourResult> tours = tourContracts.stream().map(c -> mapper.toTourResult(c, criteria)).toList();

        MarkupCalculator markupCalculator = new MarkupCalculator();
        tours.forEach(a -> markupCalculator.calculateMarkup(a, criteria));
        return tours.stream().map(mapper::toTourResultDto).toList();
    }

    private List<TourResult> mockTours() {
        TourResult result = new TourResult();
        result.setTourName("Berlin tour with guide");
        result.setTourCode("BER_01");
        result.setCityCode("BER");
        result.setCountryCode("DE");
        result.setFromDate(LocalDate.parse("2025-10-20"));
        result.setToDate(LocalDate.parse("2025-10-20"));
        result.setAdult(2);
        result.setDuration(1);
        result.setDurationType(DurationType.D);
        result.setProductType(ProductType.TOU);

        return List.of(result);
    }

    @Override
    public TourResultDto detailSearch(TourSearchCriteria criteria) {
        Specification<TourContract> spec = TourSpecification.fromCriteria(criteria);
        List<TourContract> tourContracts = tourProductRepository.findAll(spec);
        List<TourResult> tours = tourContracts.stream().map(c -> mapper.toTourResult(c, criteria)).toList();

        MarkupCalculator markupCalculator = new MarkupCalculator();
        tours.forEach(a -> markupCalculator.calculateMarkup(a, criteria));
        if (!tours.isEmpty()) {
            return mapper.toTourResultDto(tours.get(0));
        } else {
            return null;
        }
    }
}
