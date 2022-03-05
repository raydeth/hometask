package com.github.raydeth.kafka.clientapp.controller;

import com.github.raydeth.kafka.clientapp.service.ProductService;
import com.github.raydeth.kafka.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/{productId}")
    private ResponseEntity<Product> getById(@PathVariable UUID productId) {
        return ResponseEntity.ok(service.getById(productId));
    }

    @PostMapping
    private ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(service.create(product));
    }
}
