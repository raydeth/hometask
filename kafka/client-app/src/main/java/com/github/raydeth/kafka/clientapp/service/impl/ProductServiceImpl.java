package com.github.raydeth.kafka.clientapp.service.impl;

import com.github.raydeth.kafka.clientapp.repository.ProductRepository;
import com.github.raydeth.kafka.clientapp.service.ProductService;
import com.github.raydeth.kafka.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.product}")
    private String productTopic;

    @Override
    public Product create(Product product) {
        Product savedProduct = repository.save(product);
        kafkaTemplate.send(productTopic, product.getCategoryId(), product);
        return savedProduct;
    }

    @Override
    public Product getById(UUID productId) {
        return repository.findById(productId).orElseThrow(() -> new RuntimeException(""));
    }
}
