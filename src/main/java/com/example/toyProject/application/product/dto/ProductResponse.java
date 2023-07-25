package com.example.toyProject.application.product.dto;

import com.example.toyProject.domain.product.Product;
import lombok.Builder;

@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Long price;
    private String brand;

    public static ProductResponse fromEntity(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .build();
    }
}
