package com.microserviceunittestingapp.productservice.web;


import com.microserviceunittestingapp.productservice.dto.ProductDTO;
import com.microserviceunittestingapp.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable String id){
        return productService.findProductById(id);
    }
    @GetMapping("/products/search")
    public List<ProductDTO> searchProducts(@RequestParam String keyword){
        return productService.searchProducts(keyword);
    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody @Valid ProductDTO productDTO){
        return productService.saveNewProduct(productDTO);
    }
    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }
}

