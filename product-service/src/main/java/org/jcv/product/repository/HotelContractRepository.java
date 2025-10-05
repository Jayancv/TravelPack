package org.jcv.product.repository;

import org.jcv.product.model.hotel.HotelContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelContractRepository extends JpaRepository<HotelContract, String> {
}
