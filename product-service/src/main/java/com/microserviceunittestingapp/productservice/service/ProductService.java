package com.microserviceunittestingapp.productservice.service;


import com.microserviceunittestingapp.productservice.dto.ProductDTO;
import com.microserviceunittestingapp.productservice.exceptions.ProductAlreadyExistException;
import com.microserviceunittestingapp.productservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductDTO saveNewProduct(ProductDTO productDTO) throws ProductAlreadyExistException;
    List<ProductDTO> getAllProducts();
    ProductDTO findProductById(String id) throws ProductNotFoundException;
    List<ProductDTO> searchProducts(String keyword);
    ProductDTO updateProduct(String id, ProductDTO productDTO)throws ProductNotFoundException;
    void deleteProduct(String id)throws ProductNotFoundException;
}
