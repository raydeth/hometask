package com.github.raydeth.orderreceiver.service;

import com.github.raydeth.orderreceiver.enums.OrderType;
import com.github.raydeth.orderreceiver.pojo.Order;

public interface OrderService {
    void sendOrder(Order order);

    OrderType getOrderType();
}
