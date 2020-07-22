package com.occo.products.service.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.occo.products.entity.ConfigEntity;
import com.occo.products.model.Configuration;

@Component
public class ConfigurationMapper {

  public ConfigEntity toEntity(Configuration configuration) {
    ConfigEntity entity = new ConfigEntity();
    entity.setId(configuration.getId());
    entity.setName(configuration.getName());
    return entity;
  }

  public Configuration toModel(ConfigEntity entity) {
    Configuration configuration = new Configuration();
    configuration.setId(entity.getId());
    configuration.setName(entity.getName());
    return configuration;
  }

  public List<ConfigEntity> toEntity(List<Configuration> configurations) {
    List<ConfigEntity> entities = new ArrayList<>();
    configurations.stream().forEach(p -> entities.add(this.toEntity(p)));
    return entities;
  }

  public List<Configuration> toModel(List<ConfigEntity> entities) {
    List<Configuration> configurations = new ArrayList<>();
    entities.stream().forEach(e -> configurations.add(this.toModel(e)));
    return configurations;
  }

}
