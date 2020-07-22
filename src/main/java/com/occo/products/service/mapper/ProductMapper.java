package com.occo.products.service.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.occo.products.entity.ProductEntity;
import com.occo.products.model.Product;

@Component
public class ProductMapper {

  public ProductEntity toEntity(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(product.getId());
    entity.setDescription(product.getDescription());
    entity.setLink(product.getLink());
    entity.setTitle(product.getTitle());
    return entity;
  }

  public Product toModel(ProductEntity entity) {
    Product product = new Product();
    product.setId(entity.getId());
    product.setDescription(entity.getDescription());
    product.setLink(entity.getLink());
    product.setTitle(entity.getTitle());
    return product;
  }

  public List<ProductEntity> toEntity(List<Product> products) {
    List<ProductEntity> entities = new ArrayList<>();
    products.stream().forEach(p -> entities.add(this.toEntity(p)));
    return entities;
  }

  public List<Product> toModel(List<ProductEntity> entities) {
    List<Product> products = new ArrayList<>();
    entities.stream().forEach(e -> products.add(this.toModel(e)));
    return products;
  }

}
