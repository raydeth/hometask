package com.github.raydeth.orderreceiver.service.impl;

import com.github.raydeth.orderreceiver.pojo.Order;
import com.github.raydeth.orderreceiver.service.KafkaOrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOrderProducerImpl implements KafkaOrderProducer {

    @Value("${spring.kafka.topic.order.liquid.name}")
    private String liquidTopicName;

    @Value("${spring.kafka.topic.order.item.name}")
    private String itemTopicName;

    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    @Override
    public void sendLiquidOrder(Order order) {
        sendOrderToMq(liquidTopicName, order);
    }

    @Override
    public void sendItemOrder(Order order) {
        sendOrderToMq(itemTopicName, order);
    }

    private void sendOrderToMq(String topicName, Order order) {
        orderKafkaTemplate.send(topicName, order);
    }
}
