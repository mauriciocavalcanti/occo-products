package com.occo.products.model;

import java.util.List;

public class Configuration {

  private Integer id;

  private String name;

  private List<ConfigurationValue> values;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ConfigurationValue> getValues() {
    return values;
  }

  public void setValues(List<ConfigurationValue> values) {
    this.values = values;
  }

}
