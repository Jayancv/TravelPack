package org.jcv.cart.client;

import org.jcv.common.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${PRODUCT_SERVICE_URL}")
public interface ProductServiceClient {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Integer productId);

}
