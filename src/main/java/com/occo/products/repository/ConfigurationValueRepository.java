package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.occo.products.entity.ConfigValueEntity;

public interface ConfigurationValueRepository extends JpaRepository<ConfigValueEntity, Integer> {

}
