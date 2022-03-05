package com.github.raydeth.kafka.clientapp.controller;

import com.github.raydeth.kafka.clientapp.service.ProductCategoryService;
import com.github.raydeth.kafka.model.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @GetMapping("/{productCategoryId}")
    private ResponseEntity<ProductCategory> getById(@PathVariable UUID productCategoryId) {
        return ResponseEntity.ok(service.getById(productCategoryId));
    }

    @PostMapping
    private ResponseEntity<ProductCategory> create(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(service.create(productCategory));
    }
}
