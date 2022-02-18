package com.github.raydethorder.handler.service.impl;

import com.github.raydethorder.handler.model.Order;
import com.github.raydethorder.handler.service.LogService;
import com.github.raydethorder.handler.service.OrderConsumerListener;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
@RequiredArgsConstructor
public class OrderConsumerListenerImpl implements OrderConsumerListener {

    private final LogService logService;

    @Override
    @KafkaListener(topics = "accepted-orders", containerFactory = "orderSingleFactory")
    public void acceptedOrdersListener(Order order) {
        logService.logMessage("Accepted: " + order);
    }

    @Override
    @KafkaListener(topics = "rejected-orders", containerFactory = "orderSingleFactory")
    public void rejectedOrdersListener(Order order) {
        logService.logMessage("Rejected: " + order);
    }
}
