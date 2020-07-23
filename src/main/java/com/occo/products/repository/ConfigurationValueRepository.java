package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.occo.products.entity.ConfigValueEntity;

@Repository
public interface ConfigurationValueRepository extends JpaRepository<ConfigValueEntity, Integer> {

}
