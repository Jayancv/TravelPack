package org.jcv.search.mapper;

import org.jcv.common.result.dto.TourResultDto;
import org.jcv.search.model.criteria.TourSearchCriteria;
import org.jcv.search.model.data.TourContract;
import org.jcv.search.model.result.TourResult;
import org.springframework.stereotype.Component;

@Component
public class TourResultMapper {

    public TourResultDto toTourResultDto(TourResult result) {
        TourResultDto r = new TourResultDto();
        r.setTourName(result.getTourName());
        r.setTourCode(result.getTourCode());
        r.setTourType(result.getTourType());
        r.setCityCode(result.getCityCode());
        r.setCountryCode(result.getCountryCode());
        r.setSupplierName(result.getSupplierName());
        r.setDurationType(result.getDurationType());
        r.setDuration(result.getDuration());
        r.setAdult(result.getAdult());
        r.setChild(result.getChild());
        r.setFromDate(result.getFromDate());
        r.setToDate(result.getToDate());
        r.setType(result.getType());

        return r;
    }

    public TourResult toTourResult(TourContract contract, TourSearchCriteria criteria) {
        TourResult result = new TourResult();
        result.setTourName(contract.getTourName());
        result.setTourCode(contract.getTourCode());
        result.setTourType(contract.getTourType());
        result.setCityCode(contract.getCityCode());
        result.setCountryCode(contract.getCountryCode());
        result.setSupplierCode(contract.getSupplier().getCode());
        result.setSupplierName(contract.getSupplier().getName());
        result.setDurationType(criteria.getDurationType());
        result.setDuration(contract.getDuration());
        result.setAdult(criteria.getAdult());
        result.setChild(criteria.getChild());
        result.setFromDate(criteria.getFromDate());
        result.setToDate(criteria.getToDate());
        result.setType(contract.getType());

        return result;
    }
}
