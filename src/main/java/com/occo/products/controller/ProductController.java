package com.occo.products.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.occo.products.model.Combination;
import com.occo.products.model.Product;
import com.occo.products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping(value = "")
  public ResponseEntity<Object> postProduct(@Validated @RequestBody Product product) {
    try {
      product = productService.postProduct(product);
      return new ResponseEntity<>(product, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> putProduct(@Validated @RequestBody Product product,
      @PathVariable("id") Integer id) {
    try {
      productService.putProduct(product, id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "")
  public ResponseEntity<Object> getProducts() {
    try {
      List<Product> products = productService.getProducts();
      return new ResponseEntity<>(products,
          products.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getProduct(@PathVariable("id") Integer id) {
    try {
      Product product = productService.getProduct(id);
      return new ResponseEntity<>(product, product != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id) {
    try {
      productService.deleteProduct(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "{productId}/combinations")
  public ResponseEntity<Object> getCombinations(@PathVariable("productId") Integer productId) {
    try {
      List<Combination> combinations = productService.getCombinations(productId);
      return new ResponseEntity<>(combinations,
          combinations.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "{productId}/combinations")
  public ResponseEntity<Object> postCombinations(
      @Validated @RequestBody List<Combination> combinations,
      @PathVariable("productId") Integer productId) {
    try {
      combinations = productService.postCombinations(productId, combinations);
      return new ResponseEntity<>(combinations, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }


  @DeleteMapping(value = "{productId}/combinations/{combinationId}")
  public ResponseEntity<Object> deleteCombination(@PathVariable("productId") Integer productId,
      @PathVariable("combinationId") Integer combinationId) {
    try {
      productService.deleteCombination(productId, combinationId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping(value = "{productId}/combinations/{combinationId}")
  public ResponseEntity<Object> putCombination(@Validated @RequestBody Combination combination,
      @PathVariable("productId") Integer productId,
      @PathVariable("combinationId") Integer combinationId) {
    try {
      productService.putCombination(productId, combinationId, combination);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

}
