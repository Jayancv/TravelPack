package org.jcv.product.repository;

import org.jcv.product.model.tour.TourContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourContractRepository extends JpaRepository<TourContract, String> {
}
