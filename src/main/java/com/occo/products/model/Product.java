package com.occo.products.model;

import java.util.List;

public class Product {

  private Integer id;

  private String title;

  private String description;

  private String link;

  private List<Combination> combinations;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public List<Combination> getCombinations() {
    return combinations;
  }

  public void setCombinations(List<Combination> combinations) {
    this.combinations = combinations;
  }


}
