package org.jcv.cart.service;

import org.jcv.cart.model.CartItem;
import org.jcv.common.ProductType;
import org.jcv.common.result.dto.BaseResultDto;

public interface IProductSearchService<T extends BaseResultDto> {
    boolean supports(ProductType type);
    T searchProduct(CartItem item);
}