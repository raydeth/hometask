package com.github.raydeth.product.controller;

import com.github.raydeth.product.entity.ProductEntity;
import com.github.raydeth.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity getProduct(@PathVariable("id") UUID id) {
        return repository.findById(id).orElseThrow(NullPointerException::new);
    }

    @PostMapping
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return repository.save(product);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable("id") UUID id, @RequestBody ProductEntity product) {
        product.setId(id);
        return repository.save(product);
    }
}
