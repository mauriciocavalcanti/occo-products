package com.occo.products.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "config_value")
public class ConfigValueEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  private String value;

  // bi-directional many-to-one association to CombinationConfigValue
  @OneToMany(mappedBy = "configValue")
  private List<CombinationConfigValueEntity> combinationConfigValues;

  // bi-directional many-to-one association to Config
  @ManyToOne
  private ConfigEntity config;

  public ConfigValueEntity() {}

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<CombinationConfigValueEntity> getCombinationConfigValues() {
    return this.combinationConfigValues;
  }

  public void setCombinationConfigValues(List<CombinationConfigValueEntity> combinationConfigValues) {
    this.combinationConfigValues = combinationConfigValues;
  }

  public CombinationConfigValueEntity addCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().add(combinationConfigValue);
    combinationConfigValue.setConfigValue(this);

    return combinationConfigValue;
  }

  public CombinationConfigValueEntity removeCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().remove(combinationConfigValue);
    combinationConfigValue.setConfigValue(null);

    return combinationConfigValue;
  }

  public ConfigEntity getConfig() {
    return this.config;
  }

  public void setConfig(ConfigEntity config) {
    this.config = config;
  }

}
