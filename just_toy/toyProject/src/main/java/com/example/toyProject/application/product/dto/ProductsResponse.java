package com.example.toyProject.application.product.dto;

import com.example.toyProject.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsResponse {

    private final List<ProductsDetail> content;

    @Builder
    private static class ProductsDetail {
        private String name;
        private String brand;
        private Long price;

    }

    public static ProductsResponse fromEntities(List<Product> products) {
        List<ProductsDetail> productsDetails = new ArrayList<>();

        products.forEach(product -> {
            productsDetails.add(
                    ProductsDetail.builder()
                            .name(product.getName())
                            .price(product.getPrice())
                            .brand(product.getBrand()).build()
            );
        });

        return new ProductsResponse(productsDetails);
    }
}
