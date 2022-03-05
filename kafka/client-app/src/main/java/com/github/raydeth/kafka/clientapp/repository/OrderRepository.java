package com.github.raydeth.kafka.clientapp.repository;

import com.github.raydeth.kafka.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
