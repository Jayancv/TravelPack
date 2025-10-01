package org.jcv.search.repository;

import org.jcv.search.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>
{
    @Query(value = "SELECT * FROM product WHERE product_id = ?1", nativeQuery = true)
    Product getProductById( Integer productId );

}
