package org.jcv.search.mapper;

import org.jcv.common.result.dto.AlternativeDto;
import org.jcv.common.result.dto.TourResultDto;
import org.jcv.search.model.criteria.TourSearchCriteria;
import org.jcv.search.model.data.Itinerary;
import org.jcv.search.model.data.TourContract;
import org.jcv.search.model.result.Alternative;
import org.jcv.search.model.result.TourResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        r.setProductType(result.getProductType().getCode());

        r.setAlternatives(mapAlternativeDtos(result));
        r.setSelectedAlternative(mapAlternativeDto(result.getSelectedAlternative()));
        r.setCost(result.getCost());
        r.setPrice(result.getPrice());
        
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
        result.setProductType(contract.getType());
        result.setAlternatives(mapAlternatives(contract));

        return result;
    }

    private List<Alternative> mapAlternatives(TourContract contract) {
        List<Alternative> alternatives = new ArrayList<>();
        for (Itinerary itinerary : contract.getItineraries()) {
            Alternative alt = new Alternative();
            alt.setAltName(itinerary.getItineraryName());
            alt.setAltCode(itinerary.getItineraryCode());
            alt.setAdultCost(itinerary.getAdultCost());
            alt.setTeenCost(itinerary.getTeenCost());
            alt.setChildCost(itinerary.getChildCost());
            alt.setInfantCost(itinerary.getInfantCost());
            alternatives.add(alt);
        }
        return alternatives;
    }

    private List<AlternativeDto> mapAlternativeDtos(TourResult result) {
        List<AlternativeDto> alternatives = new ArrayList<>();
        for (Alternative alt : result.getAlternatives()) {
            AlternativeDto altDto = mapAlternativeDto(alt);
            alternatives.add(altDto);
        }
        return alternatives;
    }

    private static AlternativeDto mapAlternativeDto(Alternative alt) {
        AlternativeDto altDto = new AlternativeDto();
        altDto.setAltName(alt.getAltName());
        altDto.setAltCode(alt.getAltCode());
        altDto.setAdultCost(alt.getAdultCost());
        altDto.setTeenCost(alt.getTeenCost());
        altDto.setChildCost(alt.getChildCost());
        altDto.setInfantCost(alt.getInfantCost());
        altDto.setAdultPrice(alt.getAdultPrice());
        altDto.setTeenPrice(alt.getTeenPrice());
        altDto.setChildPrice(alt.getChildPrice());
        altDto.setInfantPrice(alt.getInfantPrice());

        altDto.setCost(alt.getCost());
        altDto.setPrice(alt.getPrice());
        return altDto;
    }
}
