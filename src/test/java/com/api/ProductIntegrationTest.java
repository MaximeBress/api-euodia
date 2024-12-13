package com.api;

import com.api.enums.CountryType;
import com.api.model.Product;
import com.api.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

    @BeforeEach
    public void setup() {
        product = Product.builder()
                .label("Dress")
                .price(BigDecimal.valueOf(100))
                .country(CountryType.FRANCE)
                .build();
    }

    @Test
    @Order(1)
    public void testCreateProduct() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.label").value("Dress"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.country").value(CountryType.FRANCE.toString()));
    }

    @Test
    @Order(2)
    public void getProductById() throws Exception {
        productRepository.save(product);

        mockMvc.perform(get("/product/{id}", product.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.label").value("Dress"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.country").value(CountryType.FRANCE.toString()));
    }

    @Test
    @Order(3)
    public void getPriceForProduct() throws Exception {
        productRepository.save(product);

        mockMvc.perform(get("/product/{id}/price", product.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value(120));
    }

}
