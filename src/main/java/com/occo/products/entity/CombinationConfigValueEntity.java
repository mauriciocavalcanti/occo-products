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

  // bi-directional many-to-one association to Config
  @ManyToOne
  @JoinColumn(name = "config_id", insertable = false, updatable = false)
  private ConfigEntity config;

  // bi-directional many-to-one association to ConfigValue
  @ManyToOne
  @JoinColumn(name = "config_value_id", insertable = false, updatable = false)
  private ConfigValueEntity configValue;

  // bi-directional many-to-one association to ProductCombination
  @ManyToOne
  @JoinColumn(name = "product_combination_id", insertable = false, updatable = false)
  private ProductCombinationEntity productCombination;

  public CombinationConfigValueEntity() {}

  public CombinationConfigValueEntityPK getId() {
    return this.id;
  }

  public void setId(CombinationConfigValueEntityPK id) {
    this.id = id;
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

  public ProductCombinationEntity getProductCombination() {
    return this.productCombination;
  }

  public void setProductCombination(ProductCombinationEntity productCombination) {
    this.productCombination = productCombination;
  }

}
