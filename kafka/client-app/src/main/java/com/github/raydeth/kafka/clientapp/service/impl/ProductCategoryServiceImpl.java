package com.github.raydeth.kafka.clientapp.service.impl;

import com.github.raydeth.kafka.clientapp.repository.ProductCategoryRepository;
import com.github.raydeth.kafka.clientapp.service.ProductCategoryService;
import com.github.raydeth.kafka.model.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository repository;

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.product-category}")
    private String productCategoryTopic;

    @Override
    public ProductCategory create(ProductCategory productCategory) {
        ProductCategory savedProductCategory = repository.save(productCategory);
        kafkaTemplate.send(productCategoryTopic, productCategory);
        return savedProductCategory;
    }

    @Override
    public ProductCategory getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Can not find Product category by id: " + id));
    }
}
