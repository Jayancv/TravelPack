package org.jcv.cart.mapper;

import org.jcv.cart.model.CartItem;
import org.jcv.common.ProductType;
import org.jcv.common.result.dto.BaseResultDto;

public interface IProductResultMapper<T extends BaseResultDto> {

    boolean supports(ProductType type);

    CartItem mapToCartItem(T result);

    void updateCartItemWithSearchResult(CartItem item, BaseResultDto result);
}