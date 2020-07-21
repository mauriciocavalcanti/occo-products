package com.occo.products.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CombinationEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  // bi-directional many-to-one association to CombinationConfigValue
  @OneToMany(mappedBy = "combination")
  private List<CombinationConfigValueEntity> combinationConfigValues;

  // bi-directional many-to-one association to ProductCombination
  @OneToMany(mappedBy = "combination")
  private List<ProductCombinationEntity> productCombinations;

  public CombinationEntity() {}

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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
    combinationConfigValue.setCombination(this);

    return combinationConfigValue;
  }

  public CombinationConfigValueEntity removeCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().remove(combinationConfigValue);
    combinationConfigValue.setCombination(null);

    return combinationConfigValue;
  }

  public List<ProductCombinationEntity> getProductCombinations() {
    return this.productCombinations;
  }

  public void setProductCombinations(List<ProductCombinationEntity> productCombinations) {
    this.productCombinations = productCombinations;
  }

  public ProductCombinationEntity addProductCombination(ProductCombinationEntity productCombination) {
    getProductCombinations().add(productCombination);
    productCombination.setCombination(this);

    return productCombination;
  }

  public ProductCombinationEntity removeProductCombination(ProductCombinationEntity productCombination) {
    getProductCombinations().remove(productCombination);
    productCombination.setCombination(null);

    return productCombination;
  }

}
