package com.occo.products.model;

import java.math.BigDecimal;
import java.util.List;

public class Combination {

  private Integer id;

  private Product product;

  private List<ConfigurationValue> configurationValues;

  private String image;

  private BigDecimal price;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<ConfigurationValue> getConfigurationValues() {
    return configurationValues;
  }

  public void setConfigurationValues(List<ConfigurationValue> configurationValues) {
    this.configurationValues = configurationValues;
  }

}
