package com.occo.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.occo.products.entity.ConfigEntity;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigEntity, Integer> {

}
