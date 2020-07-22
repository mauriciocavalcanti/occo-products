package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.occo.products.entity.ProductCombinationEntity;

public interface ProductCombinationRepository extends JpaRepository<ProductCombinationEntity, Integer> {

}
