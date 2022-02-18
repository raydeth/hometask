package com.github.raydeth.orderreceiver.service;

import com.github.raydeth.orderreceiver.pojo.Order;

public interface KafkaOrderProducer {
    void sendLiquidOrder(Order order);

    void sendItemOrder(Order order);
}
