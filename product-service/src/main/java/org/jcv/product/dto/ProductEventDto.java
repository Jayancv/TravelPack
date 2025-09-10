package org.jcv.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEventDto{
    public enum Type { CREATED, UPDATED, DELETED }

    private Type type;
    private int productId;
    private Instant timestamp;
    private Object payload; // can be ProductDTO

}