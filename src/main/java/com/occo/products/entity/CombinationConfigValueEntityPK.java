package com.occo.products.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CombinationConfigValueEntityPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "product_combination_id", insertable = false, updatable = false)
  private Integer productCombinationId;

  @Column(name = "config_id", insertable = false, updatable = false)
  private Integer configId;

  public CombinationConfigValueEntityPK() {}

  public Integer getProductCombinationId() {
    return this.productCombinationId;
  }

  public void setProductCombinationId(Integer productCombinationId) {
    this.productCombinationId = productCombinationId;
  }

  public Integer getConfigId() {
    return this.configId;
  }

  public void setConfigId(Integer configId) {
    this.configId = configId;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof CombinationConfigValueEntityPK)) {
      return false;
    }
    CombinationConfigValueEntityPK castOther = (CombinationConfigValueEntityPK) other;
    return this.productCombinationId.equals(castOther.productCombinationId)
        && this.configId.equals(castOther.configId);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.productCombinationId.hashCode();
    hash = hash * prime + this.configId.hashCode();

    return hash;
  }
}
