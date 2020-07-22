package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.occo.products.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
