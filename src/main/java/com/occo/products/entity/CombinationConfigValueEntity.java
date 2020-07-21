package com.occo.products.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "combination_config_value")
public class CombinationConfigValueEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private CombinationConfigValueEntityPK id;

  // bi-directional many-to-one association to Combination
  @ManyToOne
  private CombinationEntity combination;

  // bi-directional many-to-one association to Config
  @ManyToOne
  private ConfigEntity config;

  // bi-directional many-to-one association to ConfigValue
  @ManyToOne
  @JoinColumn(name = "config_value_id")
  private ConfigValueEntity configValue;

  public CombinationConfigValueEntity() {}

  public CombinationConfigValueEntityPK getId() {
    return this.id;
  }

  public void setId(CombinationConfigValueEntityPK id) {
    this.id = id;
  }

  public CombinationEntity getCombination() {
    return this.combination;
  }

  public void setCombination(CombinationEntity combination) {
    this.combination = combination;
  }

  public ConfigEntity getConfig() {
    return this.config;
  }

  public void setConfig(ConfigEntity config) {
    this.config = config;
  }

  public ConfigValueEntity getConfigValue() {
    return this.configValue;
  }

  public void setConfigValue(ConfigValueEntity configValue) {
    this.configValue = configValue;
  }

}
