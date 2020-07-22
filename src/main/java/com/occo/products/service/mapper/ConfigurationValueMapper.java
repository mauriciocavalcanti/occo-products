package com.occo.products.service.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.occo.products.entity.ConfigValueEntity;
import com.occo.products.model.ConfigurationValue;

@Component
public class ConfigurationValueMapper {

  public ConfigValueEntity toEntity(ConfigurationValue value) {
    ConfigValueEntity entity = new ConfigValueEntity();
    entity.setId(value.getId());
    entity.setValue(value.getValue());
    return entity;
  }

  public ConfigurationValue toModel(ConfigValueEntity entity) {
    ConfigurationValue value = new ConfigurationValue();
    value.setId(entity.getId());
    value.setValue(entity.getValue());
    return value;
  }

  public List<ConfigValueEntity> toEntity(List<ConfigurationValue> values) {
    List<ConfigValueEntity> entities = new ArrayList<>();
    values.stream().forEach(p -> entities.add(this.toEntity(p)));
    return entities;
  }

  public List<ConfigurationValue> toModel(List<ConfigValueEntity> entities) {
    List<ConfigurationValue> values = new ArrayList<>();
    entities.stream().forEach(e -> values.add(this.toModel(e)));
    return values;
  }

}
