package com.microserviceunittestingapp.productservice.service;

import com.microserviceunittestingapp.productservice.dto.ProductDTO;
import com.microserviceunittestingapp.productservice.entities.Product;
import com.microserviceunittestingapp.productservice.exceptions.ProductAlreadyExistException;
import com.microserviceunittestingapp.productservice.exceptions.ProductNotFoundException;
import com.microserviceunittestingapp.productservice.mapper.ProductMapper;
import com.microserviceunittestingapp.productservice.repository.ProductRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl underTest;
    @Test
    void shouldSaveNewProduct() {
        ProductDTO productDTO= ProductDTO.builder()
                .id("1L").name("Computer").price(1000).quantity(5).build();
        Product product= Product.builder()
                .id("1L").name("Computer").price(1000).quantity(5).build();
        Product savedProduct= Product.builder()
                .id("1L").name("Computer").price(1000).quantity(5).build();
        ProductDTO expected= ProductDTO.builder()
                .id("1L").name("Computer").price(1000).quantity(5).build();
        Mockito.when(productRepository.findByName(productDTO.getName())).thenReturn(Optional.empty());
        Mockito.when(productMapper.fromProductDTO(productDTO)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(savedProduct);
        Mockito.when(productMapper.fromProduct(savedProduct)).thenReturn(expected);
        ProductDTO result = underTest.saveNewProduct(productDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldNotSaveNewProductWhenNameExist() {
        ProductDTO productDTO= ProductDTO.builder()
                .name("Computer").price(1000).quantity(5).build();
        Product product= Product.builder()
                .id("1L").name("Computer").price(1000).quantity(5).build();
        Mockito.when(productRepository.findByName(productDTO.getName())).thenReturn(Optional.of(product));
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.saveNewProduct(productDTO))
                .isInstanceOf(ProductAlreadyExistException.class);
    }

    @Test
    void shouldGetAllCustomers() {
        List<Product> products = List.of(
                Product.builder().id("1L").name("Computer").price(1000).quantity(5).build(),
                Product.builder().id("2L").name("Smart Phone").price(5400).quantity(8).build()
        );
        List<ProductDTO> expected = List.of(
                ProductDTO.builder().id("1L").name("Computer").price(1000).quantity(5).build(),
                ProductDTO.builder().id("2L").name("Smart Phone").price(5400).quantity(8).build()
        );
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productMapper.fromListProducts(products)).thenReturn(expected);
        List<ProductDTO> result = underTest.getAllProducts();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldFindProductById() {
        String productId = "1L";
        Product product=Product.builder().id("1L").name("Smart Phone").price(5400).quantity(8).build();
        ProductDTO expected=ProductDTO.builder().id("1L").name("Smart Phone").price(5400).quantity(8).build();
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.fromProduct(product)).thenReturn(expected);
        ProductDTO result = underTest.findProductById(productId);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldNotFindProductById() {
        String productId = "8L";
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.findProductById(productId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(null);
    }

    @Test
    void shouldSearchProducts() {
        String keyword="m";
        List<Product> products = List.of(
                Product.builder().id("1L").name("Computer").price(1000).quantity(5).build(),
                Product.builder().id("2L").name("Smart Phone").price(5400).quantity(8).build()
        );
        List<ProductDTO> expected = List.of(
                ProductDTO.builder().id("1L").name("Computer").price(1000).quantity(5).build(),
                ProductDTO.builder().id("2L").name("Smart Phone").price(5400).quantity(8).build()
        );
        Mockito.when(productRepository.findByNameContainingIgnoreCase(keyword)).thenReturn(products);
        Mockito.when(productMapper.fromListProducts(products)).thenReturn(expected);
        List<ProductDTO> result = underTest.searchProducts(keyword);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void updateProduct() {
        String ProductId= "6L";
        ProductDTO productDTO= ProductDTO.builder()
                .id("6L").name("Computer").price(1000).quantity(5).build();
        Product product= Product.builder()
                .id("6L").name("Computer").price(1000).quantity(5).build();
        Product updatedCustomer= Product.builder()
                .id("6L").name("Computer").price(1000).quantity(5).build();
        ProductDTO expected= ProductDTO.builder()
                .id("6L").name("Computer").price(1000).quantity(5).build();
        Mockito.when(productRepository.findById(ProductId)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.fromProductDTO(productDTO)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(updatedCustomer);
        Mockito.when(productMapper.fromProduct(updatedCustomer)).thenReturn(expected);
        ProductDTO result = underTest.updateProduct(ProductId,productDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldDeleteProduct() {
        String ProductId ="1L";
        Product product= Product.builder()
                    .id("6L").name("Smart Phone").price(5400).quantity(8).build();
        Mockito.when(productRepository.findById(ProductId)).thenReturn(Optional.of(product));
        underTest.deleteProduct(ProductId);
        Mockito.verify(productRepository).deleteById(ProductId);
    }
    @Test
    void shouldNotDeleteProductIfNotExist() {
        String ProductId ="9L";
        Mockito.when(productRepository.findById(ProductId)).thenReturn(Optional.empty());
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.deleteProduct(ProductId))
                .isInstanceOf(ProductNotFoundException.class);
    }
}