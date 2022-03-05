package com.github.raydeth.kafka.clientapp.repository;

import com.github.raydeth.kafka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
