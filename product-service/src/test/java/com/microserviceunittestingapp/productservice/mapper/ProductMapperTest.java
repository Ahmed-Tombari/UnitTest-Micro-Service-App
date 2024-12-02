package com.microserviceunittestingapp.productservice.mapper;

import com.microserviceunittestingapp.productservice.dto.ProductDTO;
import com.microserviceunittestingapp.productservice.entities.Product;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper productMapper = new ProductMapper();

    @Test
    public void shouldMapProductToProductDTO() {
        Product product = Product.builder().id("1L").name("Laptop").price(1000).quantity(5).build();
        ProductDTO expected = ProductDTO.builder().id("1L").name("Laptop").price(1000).quantity(5).build();

        ProductDTO result = productMapper.fromProduct(product);

        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    public void shouldMapProductDTOToProduct() {
        ProductDTO productDto = ProductDTO.builder().id("1L").name("Laptop").price(1000).quantity(5).build();
        Product expectedProduct = Product.builder().id("1L").name("Laptop").price(1000).quantity(5).build();

        Product result = productMapper.fromProductDTO(productDto);

        AssertionsForClassTypes.assertThat(expectedProduct).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    public void shouldMapListOfProductsToListOfProductDTOs() {
        List<Product> products = List.of(
                Product.builder().id("1L").name("Laptop").price(1000).quantity(5).build(),
                Product.builder().id("2L").name("Computer").price(800).quantity(10).build()
        );

        List<ProductDTO> expectedDTOs = List.of(
                ProductDTO.builder().id("1L").name("Laptop").price(1000).quantity(5).build(),
                ProductDTO.builder().id("2L").name("Computer").price(800).quantity(10).build()
        );

        List<ProductDTO> result = productMapper.fromListProducts(products);

        AssertionsForClassTypes.assertThat(expectedDTOs).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldNotMapNullProductToProductDTO() {
        AssertionsForClassTypes.assertThatThrownBy(
                ()->productMapper.fromProduct(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}