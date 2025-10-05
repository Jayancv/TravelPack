package org.jcv.product.repository;

import org.jcv.product.model.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
