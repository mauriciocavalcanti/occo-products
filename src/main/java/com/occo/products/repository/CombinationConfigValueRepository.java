package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.occo.products.entity.CombinationConfigValueEntity;

@Repository
public interface CombinationConfigValueRepository extends JpaRepository<CombinationConfigValueEntity, Integer> {

}
