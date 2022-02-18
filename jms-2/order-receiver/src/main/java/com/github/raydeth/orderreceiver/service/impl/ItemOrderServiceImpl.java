package com.github.raydeth.orderreceiver.service.impl;

import com.github.raydeth.orderreceiver.enums.OrderType;
import com.github.raydeth.orderreceiver.pojo.Order;
import com.github.raydeth.orderreceiver.service.KafkaOrderProducer;
import com.github.raydeth.orderreceiver.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemOrderServiceImpl implements OrderService {

    private final KafkaOrderProducer kafkaOrderProducer;

    @Override
    public void sendOrder(Order order) {
        order.setVolume(null);
        kafkaOrderProducer.sendItemOrder(order);
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.ITEM;
    }
}
