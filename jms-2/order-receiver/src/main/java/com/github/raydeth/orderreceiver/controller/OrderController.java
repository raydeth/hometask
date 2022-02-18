package com.github.raydeth.orderreceiver.controller;

import com.github.raydeth.orderreceiver.pojo.Order;
import com.github.raydeth.orderreceiver.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final List<OrderService> orderServices;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        OrderService orderService = orderServices
                .stream()
                .filter(os -> os.getOrderType().equals(order.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Illegal state"));
        orderService.sendOrder(order);
        return ResponseEntity.ok(null);
    }
}
