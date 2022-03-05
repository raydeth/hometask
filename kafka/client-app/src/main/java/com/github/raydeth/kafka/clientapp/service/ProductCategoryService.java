package com.github.raydeth.kafka.clientapp.service;

import com.github.raydeth.kafka.model.ProductCategory;

import java.util.UUID;

public interface ProductCategoryService {
    ProductCategory create(ProductCategory productCategory);
    ProductCategory getById(UUID id);
}
