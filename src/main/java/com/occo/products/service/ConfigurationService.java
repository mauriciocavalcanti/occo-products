package com.occo.products.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.occo.products.entity.ConfigEntity;
import com.occo.products.entity.ConfigValueEntity;
import com.occo.products.model.Configuration;
import com.occo.products.model.ConfigurationValue;
import com.occo.products.repository.ConfigurationRepository;
import com.occo.products.repository.ConfigurationValueRepository;
import com.occo.products.service.mapper.ConfigurationMapper;
import com.occo.products.service.mapper.ConfigurationValueMapper;
import javassist.NotFoundException;

@Service
public class ConfigurationService {

  private static final String DOES_NOT_EXIST = " does not exist";

  private static final String CONFIGURATION_ID = "Configuration id: ";

  @Autowired
  ConfigurationMapper configurationMapper;

  @Autowired
  ConfigurationValueMapper configurationValueMapper;

  @Autowired
  ConfigurationRepository configurationRepository;
  
  @Autowired
  ConfigurationValueRepository configurationValueRepository;

  public Configuration postConfiguration(Configuration configuration) {
    ConfigEntity entityToSave = configurationMapper.toEntity(configuration);
    if (configuration.getValues() != null && !configuration.getValues().isEmpty()) {
      entityToSave.setConfigValues(configurationValueMapper.toEntity(configuration.getValues()));
      entityToSave.getConfigValues().stream().forEach(v -> v.setConfig(entityToSave));
    }
    ConfigEntity entitySaved = configurationRepository.save(entityToSave);
    configuration.setId(entitySaved.getId());
    return configuration;
  }

  public void putConfiguration(Configuration configuration, Integer id) throws NotFoundException {
    ConfigEntity entity = configurationRepository.findById(id).orElse(null);
    if (entity != null) {
      configuration.setId(id);
      ConfigEntity entityToSave = configurationMapper.toEntity(configuration);
      configurationRepository.save(entityToSave);
    } else {
      throw new NotFoundException(CONFIGURATION_ID + id + DOES_NOT_EXIST);
    }
  }

  public List<Configuration> getConfigurations() {
    List<ConfigEntity> entities = configurationRepository.findAll();
    return entities.stream().map(e -> {
      Configuration configuration = configurationMapper.toModel(e);
      configuration.setValues(configurationValueMapper.toModel(e.getConfigValues()));
      return configuration;
    }).collect(Collectors.toList());
  }

  public Configuration getConfiguration(Integer id) {
    ConfigEntity entity = configurationRepository.findById(id).orElse(null);
    if (entity != null) {
      Configuration configuration = configurationMapper.toModel(entity);
      configuration.setValues(configurationValueMapper.toModel(entity.getConfigValues()));
      return configuration;
    }
    return null;
  }

  public void deleteConfiguration(Integer id) throws NotFoundException {
    ConfigEntity entity = configurationRepository.findById(id).orElse(null);
    if (entity != null) {
      configurationRepository.delete(entity);
    } else {
      throw new NotFoundException(CONFIGURATION_ID + id + DOES_NOT_EXIST);
    }
  }

  public List<ConfigurationValue> postConfigurationValues(Integer configurationId,
      List<ConfigurationValue> configurationValues) throws NotFoundException {
    ConfigEntity configEntity = configurationRepository.findById(configurationId).orElse(null);
    if(configEntity != null) {
      List<ConfigValueEntity> configValueEntities = configurationValueMapper.toEntity(configurationValues);
      configValueEntities.stream().forEach(v -> v.setConfig(configEntity));
      configValueEntities = configurationValueRepository.saveAll(configValueEntities);
      return configurationValueMapper.toModel(configValueEntities);
    } else {
      throw new NotFoundException(CONFIGURATION_ID + configurationId + DOES_NOT_EXIST);
    }
  }

  public void putConfigurationValue(Integer configurationId, Integer valueId,
      ConfigurationValue configurationValue) {
    ConfigEntity configEntity = configurationRepository.findById(configurationId).orElse(null);
    ConfigValueEntity configValueEntity = configurationValueRepository.findById(valueId).orElse(null);
    if(configEntity != null && configValueEntity != null) {
      configValueEntity.setValue(configurationValue.getValue());
      configurationValueRepository.save(configValueEntity);
    }
  }

  public void deleteConfigurationValue(Integer configurationId, Integer valueId) {
    ConfigEntity configEntity = configurationRepository.findById(configurationId).orElse(null);
    ConfigValueEntity configValueEntity = configurationValueRepository.findById(valueId).orElse(null);
    if(configEntity != null && configValueEntity != null) {
      configurationValueRepository.deleteById(valueId);
    }
  }

}
