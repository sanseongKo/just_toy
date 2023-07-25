package com.example.toyProject.presentation.product.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPostRequest {
    private final String name;
    private final String brand;
    private final Long price;

}
