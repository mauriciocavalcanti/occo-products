package com.occo.products.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.occo.products.entity.CombinationConfigValueEntity;
import com.occo.products.entity.CombinationConfigValueEntityPK;
import com.occo.products.entity.ProductCombinationEntity;
import com.occo.products.entity.ProductEntity;
import com.occo.products.model.Combination;
import com.occo.products.model.ConfigurationValue;
import com.occo.products.model.Product;
import com.occo.products.repository.CombinationConfigValueRepository;
import com.occo.products.repository.ProductCombinationRepository;
import com.occo.products.repository.ProductRepository;
import com.occo.products.service.mapper.CombinationMapper;
import com.occo.products.service.mapper.ConfigurationMapper;
import com.occo.products.service.mapper.ConfigurationValueMapper;
import com.occo.products.service.mapper.ProductMapper;
import javassist.NotFoundException;

@Service
public class ProductService {

  private static final String DOES_NOT_EXIST = " does not exist";

  private static final String PRODUCT_ID = "Product id: ";

  @Autowired
  ProductMapper productMapper;

  @Autowired
  CombinationMapper combinationMapper;

  @Autowired
  ConfigurationMapper configurationMapper;

  @Autowired
  ConfigurationValueMapper configurationValueMapper;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductCombinationRepository combinationRepository;

  @Autowired
  CombinationConfigValueRepository combinationConfigValueRepository;

  @Transactional
  public Product postProduct(Product product) {
    ProductEntity productToSave = productMapper.toEntity(product);
    ProductEntity productSaved = productRepository.save(productToSave);

    if (product.getCombinations() != null && !product.getCombinations().isEmpty()) {
      saveExtractedCombinations(product.getCombinations(), productSaved);
    }

    return productMapper.toModel(productSaved);
  }


  public void putProduct(Product product, Integer id) {
    product.setId(id);
    productRepository.save(productMapper.toEntity(product));
  }

  public List<Product> getProducts() {
    List<ProductEntity> entities = productRepository.findAll();
    return entities.stream().map(e -> {
      List<Combination> combinations = extractCombinationsFromProductEntity(e);
      Product product = productMapper.toModel(e);
      product.setCombinations(combinations);
      return product;
    }).collect(Collectors.toList());
  }



  public Product getProduct(Integer id) {
    ProductEntity entity = productRepository.findById(id).orElse(null);
    if (entity != null) {
      List<Combination> combinations = extractCombinationsFromProductEntity(entity);
      Product product = productMapper.toModel(entity);
      product.setCombinations(combinations);
      return product;
    }
    return null;
  }

  public void deleteProduct(Integer id) throws NotFoundException {
    ProductEntity entityToDelete = productRepository.findById(id).orElse(null);
    if (entityToDelete != null) {
      productRepository.delete(entityToDelete);
    } else {
      throw new NotFoundException(PRODUCT_ID + id + DOES_NOT_EXIST);
    }
  }

  public List<Combination> getCombinations(Integer productId) throws NotFoundException {
    ProductEntity productEntity = productRepository.findById(productId).orElse(null);
    if (productEntity != null) {
      return extractCombinationsFromProductEntity(productEntity);
    }
    throw new NotFoundException(PRODUCT_ID + productId + DOES_NOT_EXIST);
  }

  public void deleteCombination(Integer productId, Integer combinationId) throws NotFoundException {
    ProductEntity productEntity = productRepository.findById(productId).orElse(null);
    ProductCombinationEntity combinationEntity =
        combinationRepository.findById(combinationId).orElse(null);
    if (productEntity != null && combinationEntity != null) {
      combinationRepository.deleteById(combinationId);
    } else {
      throw new NotFoundException(
          PRODUCT_ID + productId + "or Combination id: " + combinationId + DOES_NOT_EXIST);
    }

  }

  public void putCombination(Integer productId, Integer combinationId, Combination combination)
      throws NotFoundException {
    ProductEntity productEntity = productRepository.findById(productId).orElse(null);
    ProductCombinationEntity combinationEntity =
        combinationRepository.findById(combinationId).orElse(null);
    if (productEntity != null && combinationEntity != null) {
      saveExtractedCombination(combination, productEntity);
    } else {
      throw new NotFoundException(
          PRODUCT_ID + productId + "or Combination id: " + combinationId + DOES_NOT_EXIST);
    }

  }

  public List<Combination> postCombinations(Integer productId, List<Combination> combinations)
      throws NotFoundException {
    ProductEntity productEntity = productRepository.findById(productId).orElse(null);
    if (productEntity != null) {
      saveExtractedCombinations(combinations, productEntity);
      return combinations;
    }
    throw new NotFoundException(PRODUCT_ID + productId + DOES_NOT_EXIST);
  }

  private void saveExtractedCombinations(List<Combination> combinations,
      ProductEntity productEntity) {
    combinations.stream().forEach(c -> saveExtractedCombination(c, productEntity));
  }


  private void saveExtractedCombination(Combination c, ProductEntity productEntity) {
    ProductCombinationEntity combinationToSave = combinationMapper.toEntity(c);
    combinationToSave.setProduct(productEntity);
    ProductCombinationEntity combinationSaved = combinationRepository.save(combinationToSave);
    c.getConfigurationValues().stream().forEach(config -> {
      CombinationConfigValueEntity combinationConfigValueEntity =
          new CombinationConfigValueEntity();
      CombinationConfigValueEntityPK combinationConfigValuePK =
          new CombinationConfigValueEntityPK();
      combinationConfigValuePK.setConfigId(config.getConfiguration().getId());
      combinationConfigValuePK.setProductCombinationId(combinationSaved.getId());
      combinationConfigValueEntity.setConfigValue(configurationValueMapper.toEntity(config));
      combinationConfigValueEntity.setId(combinationConfigValuePK);
      combinationConfigValueRepository.save(combinationConfigValueEntity);
    });
    c.setId(combinationSaved.getId());
  }

  private List<Combination> extractCombinationsFromProductEntity(ProductEntity e) {
    return e.getProductCombinations().stream().map(this::extractSingleCombinationFromEntity)
        .collect(Collectors.toList());
  }


  private Combination extractSingleCombinationFromEntity(ProductCombinationEntity c) {
    List<ConfigurationValue> configValues = c.getCombinationConfigValues().stream().map(cV -> {
      ConfigurationValue configValue = configurationValueMapper.toModel(cV.getConfigValue());
      configValue.setConfiguration(configurationMapper.toModel(cV.getConfig()));
      return configValue;
    }).collect(Collectors.toList());
    Combination combination = combinationMapper.toModel(c);
    combination.setConfigurationValues(configValues);
    return combination;
  }
}
