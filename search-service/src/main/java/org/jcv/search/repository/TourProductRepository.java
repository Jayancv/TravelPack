package org.jcv.search.repository;

import org.jcv.search.model.data.TourContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TourProductRepository extends JpaRepository<TourContract, Long>, JpaSpecificationExecutor<TourContract> {
}
