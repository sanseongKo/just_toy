package com.example.toyProject.presentation.product;

import com.example.toyProject.application.product.dto.ProductResponse;
import com.example.toyProject.application.product.ProductService;
import com.example.toyProject.application.product.dto.ProductsResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @PostMapping
    public void saveProduct() {

    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public ProductsResponse getProductList() {
        return productService.getProducts();
    }
}