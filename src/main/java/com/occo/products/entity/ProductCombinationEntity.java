package com.occo.products.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_combination")
@NamedQuery(name = "ProductCombinationEntity.findAll",
    query = "SELECT p FROM ProductCombinationEntity p")
public class ProductCombinationEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String image;

  private BigDecimal price;

  // bi-directional many-to-one association to CombinationConfigValue
  @OneToMany(mappedBy = "productCombination")
  private List<CombinationConfigValueEntity> combinationConfigValues;

  // bi-directional many-to-one association to Product
  @ManyToOne
  private ProductEntity product;

  public ProductCombinationEntity() {}

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
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
    combinationConfigValue.setProductCombination(this);

    return combinationConfigValue;
  }

  public CombinationConfigValueEntity removeCombinationConfigValue(
      CombinationConfigValueEntity combinationConfigValue) {
    getCombinationConfigValues().remove(combinationConfigValue);
    combinationConfigValue.setProductCombination(null);

    return combinationConfigValue;
  }

  public ProductEntity getProduct() {
    return this.product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

}
