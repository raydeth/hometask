package com.github.raydeth.kafka.clientapp.service;

import com.github.raydeth.kafka.model.Order;
import com.github.raydeth.kafka.model.OrderState;

import java.util.UUID;

public interface OrderService {
    Order create(Order order);

    Order createNewOrder(Order order);

    Order updateState(UUID orderId, OrderState state);

    Order getById(UUID id);
}
