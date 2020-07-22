package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.occo.products.entity.ConfigEntity;

public interface ConfigurationRepository extends JpaRepository<ConfigEntity, Integer> {

}
