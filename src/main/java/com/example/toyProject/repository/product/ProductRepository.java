package com.example.toyProject.repository.product;

import com.example.toyProject.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{}