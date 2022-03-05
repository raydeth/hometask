package com.github.raydeth.kafka.clientapp.service;

import com.github.raydeth.kafka.model.Product;

import java.util.UUID;

public interface ProductService {
    Product create(Product product);

    Product getById(UUID productId);
}
