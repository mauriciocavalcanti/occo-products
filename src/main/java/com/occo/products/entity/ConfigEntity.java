package com.occo.products.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "config")
@NamedQuery(name = "ConfigEntity.findAll", query = "SELECT c FROM ConfigEntity c")
public class ConfigEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  // bi-directional many-to-one association to CombinationConfigValue
  @OneToMany(mappedBy = "config")
  private List<CombinationConfigValueEntity> combinationConfigValues;

  // bi-directional many-to-one association to ConfigValue
  @OneToMany(mappedBy = "config", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<ConfigValueEntity> configValues;

  public ConfigEntity() {}

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CombinationConfigValueEntity> getCombinationConfigValues() {
    return this.combinationConfigValues;
  }

  public void setCombinationConfigValues(
      List<CombinationConfigValueEntity> combinationConfigValues) {
    this.combinationConfigValues = combinationConfigValues;
  }

  public CombinationConfigValueEntity addCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().add(combinationConfigValue);
    combinationConfigValue.setConfig(this);

    return combinationConfigValue;
  }

  public CombinationConfigValueEntity removeCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().remove(combinationConfigValue);
    combinationConfigValue.setConfig(null);

    return combinationConfigValue;
  }

  public List<ConfigValueEntity> getConfigValues() {
    return this.configValues;
  }

  public void setConfigValues(List<ConfigValueEntity> configValues) {
    this.configValues = configValues;
  }

  public ConfigValueEntity addConfigValue(ConfigValueEntity configValue) {
    getConfigValues().add(configValue);
    configValue.setConfig(this);

    return configValue;
  }

  public ConfigValueEntity removeConfigValue(ConfigValueEntity configValue) {
    getConfigValues().remove(configValue);
    configValue.setConfig(null);

    return configValue;
  }

}
