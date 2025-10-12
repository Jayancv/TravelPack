package org.jcv.cart.service;

import org.jcv.cart.client.SearchServiceClient;
import org.jcv.cart.model.CartItem;
import org.jcv.cart.model.TourItem;
import org.jcv.common.ProductType;
import org.jcv.common.result.dto.TourResultDto;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class TourProductSearchService implements IProductSearchService<TourResultDto> {

    private final SearchServiceClient searchServiceClient;

    public TourProductSearchService(SearchServiceClient searchServiceClient) {
        this.searchServiceClient = searchServiceClient;
    }

    @Override
    public boolean supports(ProductType type) {
        return type == ProductType.TOU;
    }

    @Override
    public TourResultDto searchProduct(CartItem item) {
        TourItem tourItem = (TourItem) item;

        TourResultDto tour = searchServiceClient.getDetailTourProduct(
                tourItem.getAdult(),
                tourItem.getChild(),
                tourItem.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                tourItem.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                tourItem.getDuration(),
                tourItem.getDurationType().getCode(),
                tourItem.getTourCode(),
                tourItem.getTourName(),
                tourItem.getCityCode(),
                tourItem.getTourType(),
                false
        );

        return tour;
    }
}
