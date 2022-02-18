package com.github.raydethorder.handler.service;

import com.github.raydethorder.handler.model.Order;

public interface OrderConsumerListener {
    void acceptedOrdersListener(Order order);

    void rejectedOrdersListener(Order order);
}
