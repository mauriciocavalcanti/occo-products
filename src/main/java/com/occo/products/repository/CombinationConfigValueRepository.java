package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.occo.products.entity.CombinationConfigValueEntity;

public interface CombinationConfigValueRepository extends JpaRepository<CombinationConfigValueEntity, Integer> {

}
