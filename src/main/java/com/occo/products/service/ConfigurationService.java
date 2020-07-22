package com.occo.products.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.occo.products.entity.ConfigEntity;
import com.occo.products.model.Configuration;
import com.occo.products.repository.ConfigurationRepository;
import com.occo.products.service.mapper.ConfigurationMapper;
import com.occo.products.service.mapper.ConfigurationValueMapper;

@Service
public class ConfigurationService {

  @Autowired
  ConfigurationMapper configurationMapper;

  @Autowired
  ConfigurationValueMapper configurationValueMapper;

  @Autowired
  ConfigurationRepository configurationRepository;

  public Configuration postConfiguration(Configuration configuration) {
    ConfigEntity entityToSave = configurationMapper.toEntity(configuration);
    entityToSave.setConfigValues(configurationValueMapper.toEntity(configuration.getValues()));
    entityToSave.getConfigValues().stream().forEach(v -> v.setConfig(entityToSave));
    ConfigEntity entitySaved = configurationRepository.save(entityToSave);
    configuration.setId(entitySaved.getId());
    return configuration;
  }

  public void putConfiguration(Configuration configuration, Integer id) {
    // TODO Auto-generated method stub
    
  }

  public List<Configuration> getConfigurations() {
    // TODO Auto-generated method stub
    return null;
  }

  public Configuration getConfiguration(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteConfiguration(Integer id) {
    // TODO Auto-generated method stub
    
  }

}
