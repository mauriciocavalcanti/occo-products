package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.occo.products.entity.ProductCombinationEntity;

@Repository
public interface ProductCombinationRepository extends JpaRepository<ProductCombinationEntity, Integer> {

}
