package com.github.raydeth.kafka.clientapp.repository;

import com.github.raydeth.kafka.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
