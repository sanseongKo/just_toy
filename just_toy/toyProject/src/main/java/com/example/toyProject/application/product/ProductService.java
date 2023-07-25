package com.example.toyProject.application.product;

import com.example.toyProject.application.product.dto.ProductResponse;
import com.example.toyProject.application.product.dto.ProductsResponse;
import com.example.toyProject.domain.product.Product;
import com.example.toyProject.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        //TODO log 출력

        return ProductResponse.fromEntity(product);
    }

    public ProductsResponse getProducts() {
        List<Product> products = productRepository.findAll();

        return ProductsResponse.fromEntities(products);
    }
}
