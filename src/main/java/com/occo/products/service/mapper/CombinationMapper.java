package com.occo.products.service.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.occo.products.entity.ProductCombinationEntity;
import com.occo.products.model.Combination;

@Component
public class CombinationMapper {

  public ProductCombinationEntity toEntity(Combination combination) {
    ProductCombinationEntity entity = new ProductCombinationEntity();
    entity.setId(combination.getId());
    entity.setImage(combination.getImage());
    entity.setPrice(combination.getPrice());
    return entity;
  }

  public Combination toModel(ProductCombinationEntity entity) {
    Combination combination = new Combination();
    combination.setId(entity.getId());
    combination.setImage(entity.getImage());
    combination.setPrice(entity.getPrice());
    return combination;
  }

  public List<ProductCombinationEntity> toEntity(List<Combination> combinations) {
    List<ProductCombinationEntity> entities = new ArrayList<>();
    combinations.stream().forEach(c -> entities.add(this.toEntity(c)));
    return entities;
  }

  public List<Combination> toModel(List<ProductCombinationEntity> entities) {
    List<Combination> combinations = new ArrayList<>();
    entities.stream().forEach(e -> combinations.add(this.toModel(e)));
    return combinations;
  }

}
