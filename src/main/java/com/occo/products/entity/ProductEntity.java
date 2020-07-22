package com.occo.products.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String description;

  private String link;

  private String title;

  // bi-directional many-to-one association to ProductCombination
  @OneToMany(mappedBy = "product")
  private List<ProductCombinationEntity> productCombinations;

  public ProductEntity() {}

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return this.link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<ProductCombinationEntity> getProductCombinations() {
    return this.productCombinations;
  }

  public void setProductCombinations(List<ProductCombinationEntity> productCombinations) {
    this.productCombinations = productCombinations;
  }

  public ProductCombinationEntity addProductCombination(ProductCombinationEntity productCombination) {
    getProductCombinations().add(productCombination);
    productCombination.setProduct(this);

    return productCombination;
  }

  public ProductCombinationEntity removeProductCombination(ProductCombinationEntity productCombination) {
    getProductCombinations().remove(productCombination);
    productCombination.setProduct(null);

    return productCombination;
  }

}
