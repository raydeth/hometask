package com.github.raydeth.kafka.clientapp.controller;

import com.github.raydeth.kafka.clientapp.service.OrderService;
import com.github.raydeth.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/{orderId}")
    private ResponseEntity<Order> getById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(service.getById(orderId));
    }

    @PostMapping
    private ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(service.createNewOrder(order));
    }
}
