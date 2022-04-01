package com.github.raydeth.service;

import com.github.raydeth.enums.OrderProcessor;
import com.github.raydeth.enums.ProcessedQueues;
import com.github.raydeth.model.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderService {

    private static final long TOTAL_THRESHOLD = 500;

    private OrderQueueProducer orderQueueProducer;

    public OrderService() {
        this.orderQueueProducer = new OrderQueueProducer();
    }

    @SneakyThrows
    public void processOrder(Order order) {
        boolean isValid = OrderProcessor.getByOrderType(order.getType()).getProcessor().test(order) && TOTAL_THRESHOLD - order.getTotal() > 0;
        String queueName = ProcessedQueues.getByCondition(isValid).getQueueName();
        log.info(String.format("Order with username: %s send to queue: %s", order.getUser(), queueName));
        orderQueueProducer.send(order, queueName);
    }
}
