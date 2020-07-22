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
import com.occo.products.model.Configuration;
import com.occo.products.service.ConfigurationService;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {
  
  @Autowired
  private ConfigurationService configurationService;

  @PostMapping(value = "")
  public ResponseEntity<Object> postConfiguration(@Validated @RequestBody Configuration configuration) {
    try {
      configuration = configurationService.postConfiguration(configuration);
      return new ResponseEntity<>(configuration, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> putConfiguration(@Validated @RequestBody Configuration configuration,
      @PathVariable("id") Integer id) {
    try {
      configurationService.putConfiguration(configuration, id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "")
  public ResponseEntity<Object> getConfigurations() {
    try {
      List<Configuration> configurations = configurationService.getConfigurations();
      return new ResponseEntity<>(configurations,
          configurations.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> getConfiguration(@PathVariable("id") Integer id) {
    try {
      Configuration configuration = configurationService.getConfiguration(id);
      return new ResponseEntity<>(configuration, configuration != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteConfiguration(@PathVariable("id") Integer id) {
    try {
      configurationService.deleteConfiguration(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
