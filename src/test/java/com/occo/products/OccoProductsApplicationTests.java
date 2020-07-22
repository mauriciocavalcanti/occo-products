package com.occo.products;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
class OccoProductsApplicationTests {

  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  private static final String PRODUCT1 =
      "{ \"title\": \"Some Chair\",  \"description\": \"just a nice chair\", \"link\": \"www.google.com\"}";

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @BeforeEach
  private void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void createUser() throws Exception {
    mockMvc.perform(post("/products").contentType(CONTENT_TYPE).content(PRODUCT1))
        .andExpect(status().isCreated());
  }
}
