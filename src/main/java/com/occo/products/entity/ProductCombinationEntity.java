package com.occo.products.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_combination")
public class ProductCombinationEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  private String image;

  private BigDecimal price;

  // bi-directional many-to-one association to Combination
  @ManyToOne
  private CombinationEntity combination;

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

  public CombinationEntity getCombination() {
    return this.combination;
  }

  public void setCombination(CombinationEntity combinationEntity) {
    this.combination = combinationEntity;
  }

  public ProductEntity getProduct() {
    return this.product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

}
