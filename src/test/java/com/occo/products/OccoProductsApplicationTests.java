package com.occo.products;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.occo.products.entity.ConfigEntity;
import com.occo.products.entity.ConfigValueEntity;
import com.occo.products.entity.ProductEntity;
import com.occo.products.repository.ConfigurationRepository;
import com.occo.products.repository.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
class OccoProductsApplicationTests {

  private static boolean isInitialized;
  private static Integer configId;
  private static Integer configValueId;
  private static Integer productId;
  private static Integer productToDeleteId;
  private static Integer productWithCombinationId;
  private static Integer combinationId;
  private static Integer configurationId;

  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  private static final String PRODUCT_WITHOUT_COMBINATIONS =
      "{ \"title\": \"Some Chair\",  \"description\": \"just a nice chair\", \"link\": \"www.google.com\"}";
  private static final String PRODUCT_WITHOUT_COMBINATIONS2 =
      "{ \"title\": \"Some Chair\",  \"description\": \"just a nice chair\", \"link\": \"www.yahoo.com\"}";
  private static final String PRODUCT_WITH_COMBINATIONS =
      "{    \"title\": \"Some Chair\",  \"description\": \"just a nice chair\", \"link\": \"www.google.com\",   \"combinations\": [{        \"image\": \"image\",       \"price\": 123.11,      \"configurationValues\": [{\"id\":           configValueId, \"configuration\": {\"id\": configId} }]  }]          }";

  private static final String COMBINATION =
      "[{        \"image\": \"image\",       \"price\": 123.11,      \"configurationValues\": [{\"id\":           configValueId, \"configuration\": {\"id\": configId} }]  }]";
  private static final String COMBINATION_TO_UPDATE =
      "{        \"image\": \"image5\",       \"price\": 123.11,      \"configurationValues\": [{\"id\":           configValueId, \"configuration\": {\"id\": configId} }]  }";
  private static final String CONFIGURATION =
      "{    \"name\": \"material\", \"values\": [     {\"value\": \"wood\"},      {\"value\": \"plastic\"},       {\"value\": \"fabric\"},        {\"value\": \"iron\"}       ]}";
  private static final String CONFIGURATION_TO_UPDATE =
      "{    \"name\": \"some other name\", \"values\": [     {\"value\": \"wood\"},      {\"value\": \"plastic\"},       {\"value\": \"fabric\"},        {\"value\": \"iron\"}       ]}";

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ConfigurationRepository configRepository;

  @Autowired
  private ProductRepository productRepository;

  private MockMvc mockMvc;

  @BeforeEach
  private void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    if (!isInitialized) {
      ConfigEntity config = new ConfigEntity();
      config.setName("material");
      ConfigValueEntity configValues = new ConfigValueEntity();
      configValues.setValue("wood");
      configValues.setConfig(config);
      config.setConfigValues(Arrays.asList(configValues));
      ConfigEntity saved = configRepository.save(config);
      ProductEntity product = new ProductEntity();
      product.setDescription("description");
      product.setLink("link");
      product.setTitle("title");
      productId = productRepository.save(product).getId();
      ProductEntity productToDelete = new ProductEntity();
      productToDelete.setDescription("description");
      productToDelete.setLink("link");
      productToDelete.setTitle("title");
      productToDeleteId = productRepository.save(productToDelete).getId();
      configId = saved.getId();
      configValueId = saved.getConfigValues().get(0).getId();
      isInitialized = true;
    }
  }

  @Test
  @Order(1)
  public void createProduct_withCombinations() throws Exception {
    MvcResult result = mockMvc
        .perform(post("/products").contentType(CONTENT_TYPE)
            .content(PRODUCT_WITH_COMBINATIONS.replaceAll("configId", configId.toString())
                .replaceAll("configValueId", configValueId.toString())))
        .andExpect(status().isCreated()).andReturn();
    String body = result.getResponse().getContentAsString();
    productWithCombinationId = Integer.valueOf(body.trim().split(":")[1].substring(0, 1));
  }

  @Test
  public void createProduct_withoutCombinations() throws Exception {
    mockMvc
        .perform(post("/products").contentType(CONTENT_TYPE).content(PRODUCT_WITHOUT_COMBINATIONS))
        .andExpect(status().isCreated());
  }

  @Test
  public void updateProduct() throws Exception {
    mockMvc.perform(put("/products/" + productId).contentType(CONTENT_TYPE)
        .content(PRODUCT_WITHOUT_COMBINATIONS2)).andExpect(status().isNoContent());
  }

  @Test
  public void updateProduct_inexistent() throws Exception {
    mockMvc.perform(put("/products/" + 123123123).contentType(CONTENT_TYPE)
        .content(PRODUCT_WITHOUT_COMBINATIONS2)).andExpect(status().isBadRequest());
  }

  @Test
  public void deleteProduct() throws Exception {
    mockMvc.perform(delete("/products/" + productToDeleteId)).andExpect(status().isNoContent());
  }

  @Test
  public void deleteProduct_inexistent() throws Exception {
    mockMvc.perform(delete("/products/" + 123123)).andExpect(status().isBadRequest());
  }

  @Test
  public void getProducts() throws Exception {
    mockMvc.perform(get("/products/")).andExpect(status().isOk());
  }

  @Test
  public void getProduct() throws Exception {
    mockMvc.perform(get("/products/" + productId)).andExpect(status().isOk());
  }

  @Test
  public void getProduct_inexistent() throws Exception {
    mockMvc.perform(get("/products/" + 1231231123)).andExpect(status().isNoContent());
  }

  @Test
  @Order(1)
  public void getCombinations_onProductWithoutCombinations() throws Exception {
    mockMvc.perform(get("/products/" + productId + "/combinations"))
        .andExpect(status().isNoContent());
  }

  @Test
  @Order(2)
  public void getCombinations_onProductWithCombinations() throws Exception {
    mockMvc.perform(get("/products/" + productWithCombinationId + "/combinations"))
        .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  public void createCombinations() throws Exception {
    MvcResult result = mockMvc
        .perform(post("/products/" + productId + "/combinations").contentType(CONTENT_TYPE)
            .content(COMBINATION.replaceAll("configId", configId.toString())
                .replaceAll("configValueId", configValueId.toString())))
        .andExpect(status().isCreated()).andReturn();
    String body = result.getResponse().getContentAsString();
    combinationId = Integer.valueOf(body.trim().split(":")[1].substring(0, 1));
  }

  @Test
  @Order(4)
  public void deleteCombination() throws Exception {
    mockMvc.perform(delete("/products/" + productId + "/combinations/" + combinationId))
        .andExpect(status().isNoContent());
  }

  @Test
  @Order(3)
  public void updateCombination() throws Exception {
    mockMvc
        .perform(put("/products/" + productId + "/combinations/" + combinationId)
            .contentType(CONTENT_TYPE)
            .content(COMBINATION_TO_UPDATE.replaceAll("configId", configId.toString())
                .replaceAll("configValueId", configValueId.toString())))
        .andExpect(status().isNoContent());
  }

  @Test
  @Order(5)
  public void createConfigurations() throws Exception {
    MvcResult result =
        mockMvc.perform(post("/configurations").contentType(CONTENT_TYPE).content(CONFIGURATION))
            .andExpect(status().isCreated()).andReturn();
    String body = result.getResponse().getContentAsString();
    configurationId = Integer.valueOf(body.trim().split(":")[1].substring(0, 1));
  }

  @Test
  @Order(6)
  public void updateConfigurations() throws Exception {
    mockMvc.perform(put("/configurations/" + configurationId).contentType(CONTENT_TYPE)
        .content(CONFIGURATION_TO_UPDATE)).andExpect(status().isNoContent());
  }

  @Test
  @Order(6)
  public void getConfigurations() throws Exception {
    mockMvc.perform(get("/configurations/")).andExpect(status().isOk());
  }

  @Test
  @Order(6)
  public void getConfiguration() throws Exception {
    mockMvc.perform(get("/configurations/" + configurationId)).andExpect(status().isOk());
  }

  @Test
  @Order(7)
  public void deleteConfigurationValue_existingCombination_shouldBeBadRequest() throws Exception {
    mockMvc.perform(delete("/configurations/" + configId + "/values/" + configValueId))
        .andExpect(status().isBadRequest());
  }

  @Test
  @Order(8)
  public void deleteConfiguration() throws Exception {
    mockMvc.perform(delete("/configurations/" + configurationId)).andExpect(status().isNoContent());
  }
}
