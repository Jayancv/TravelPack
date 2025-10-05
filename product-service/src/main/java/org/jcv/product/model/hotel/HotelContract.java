package org.jcv.product.model.hotel;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcv.product.model.Contract;
import org.jcv.common.ProductType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelContract extends Contract {

    private String hotelName;
    private String hotelCode;


    @Override
    public ProductType getProductType() {
        return ProductType.HTL;
    }
}
