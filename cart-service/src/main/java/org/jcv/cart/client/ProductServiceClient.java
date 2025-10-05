package org.jcv.cart.client;

import org.jcv.common.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080/api/v1/product-service")
public interface ProductServiceClient {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Integer productId);

}
