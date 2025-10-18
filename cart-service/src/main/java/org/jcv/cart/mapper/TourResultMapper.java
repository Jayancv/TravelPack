package org.jcv.cart.mapper;

import org.jcv.cart.model.CartItem;
import org.jcv.cart.model.TourItem;
import org.jcv.cart.model.supplier.Supplier;
import org.jcv.common.ProductType;
import org.jcv.common.result.dto.BaseResultDto;
import org.jcv.common.result.dto.TourResultDto;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class TourResultMapper implements IProductResultMapper<TourResultDto> {

    @Override
    public boolean supports(ProductType type) {
        return type == ProductType.TOU;
    }

    @Override
    public CartItem mapToCartItem(TourResultDto result) {
        TourItem cartItem = new TourItem();
        cartItem.setTourName(result.getTourName());
        cartItem.setTourCode(result.getTourCode());
        cartItem.setTourType(result.getTourType());
        cartItem.setCityCode(result.getCityCode());
        cartItem.setCountryCode(result.getCountryCode());

        Supplier sup = new Supplier();
        sup.setCode(result.getSupplierCode());
        sup.setName(result.getSupplierName());
        cartItem.setSupplier(sup);

        cartItem.setDurationType(result.getDurationType());
        cartItem.setDuration(result.getDuration());
        cartItem.setAdult(result.getAdult());
        cartItem.setChild(result.getChild());
        cartItem.setFromDate(Date.from(result.getFromDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        cartItem.setToDate(Date.from(result.getToDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        cartItem.setProductType(ProductType.fromCode(result.getProductType()));

        return cartItem;
    }

    @Override
    public void updateCartItemWithSearchResult(CartItem item, BaseResultDto result) {
        ((TourItem) item).setTourName(((TourResultDto) result).getTourName());
        ((TourItem) item).setTourCode(((TourResultDto) result).getTourCode());
        item.setPrice(result.getPrice());
    }
}
