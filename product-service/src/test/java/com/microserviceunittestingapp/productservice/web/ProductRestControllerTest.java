package com.microserviceunittestingapp.productservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microserviceunittestingapp.productservice.dto.ProductDTO;
import com.microserviceunittestingapp.productservice.exceptions.ProductNotFoundException;
import com.microserviceunittestingapp.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.hamcrest.Matchers;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {
    @MockitoBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    List<ProductDTO> products;

    @BeforeEach
    void setUp() {
        this.products = List.of(
                ProductDTO.builder().id("1L").name("Computer").price(3200).quantity(11).build(),
                ProductDTO.builder().id("1L").name("Printer").price(1299).quantity(10).build(),
                ProductDTO.builder().id("1L").name("Smart Phone").price(5400).quantity(8).build()
        );
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    void shouldGetProductById() throws Exception {
        String id = "1L";
        Mockito.when(productService.findProductById(id)).thenReturn(products.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(products.get(0))));
    }

    @Test
    void shouldNotGetProductByInvalidId() throws Exception {
        String id = "9L";
        Mockito.when(productService.findProductById(id)).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void searchProducts() throws Exception {
        String keyword="m";
        Mockito.when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products?keyword="+keyword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    void shouldSaveProduct() throws Exception {
        ProductDTO productDTO= products.get(0);
        String expected = """
                {
                  "id":"1L", "name":"Computer", "price":3200, "quantity":11
                }
                """;
        Mockito.when(productService.saveNewProduct(Mockito.any())).thenReturn(products.get(0));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    void testUpdateProduct() throws Exception {
        String ProductId = "1L";
        ProductDTO productDTO= products.get(0);
        Mockito.when(productService.updateProduct(Mockito.eq(ProductId),Mockito.any())).thenReturn(products.get(0));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{id}", ProductId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(productDTO)));
    }
    @Test
    void shouldDeleteProduct() throws Exception {
        Long ProductId=1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}",ProductId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}