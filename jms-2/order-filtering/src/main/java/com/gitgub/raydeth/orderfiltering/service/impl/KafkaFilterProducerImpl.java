package com.gitgub.raydeth.orderfiltering.service.impl;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import com.gitgub.raydeth.orderfiltering.service.KafkaFilterProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaFilterProducerImpl implements KafkaFilterProducer {

    @Value("${kafka.topic.filter.accepted.name}")
    private String acceptedOrderTopic;

    @Value("${kafka.topic.filter.rejected.name}")
    private String rejectedOrderTopic;

    private final KafkaTemplate<String, LiquidOrder> liquidOrderKafkaTemplate;

    private final KafkaTemplate<String, ItemOrder> itemOrderKafkaTemplate;

    @Override
    public void sendLiquidAcceptedOrder(LiquidOrder order) {
        liquidOrderKafkaTemplate.send(acceptedOrderTopic, order.getUserName(), order);
    }

    @Override
    public void sendLiquidRejectedOrder(LiquidOrder order) {
        liquidOrderKafkaTemplate.send(rejectedOrderTopic, order.getUserName(), order);
    }

    @Override
    public void sendItemAcceptedOrder(ItemOrder order) {
        itemOrderKafkaTemplate.send(acceptedOrderTopic, order.getUserName(), order);
    }

    @Override
    public void sendItemRejectedOrder(ItemOrder order) {
        itemOrderKafkaTemplate.send(rejectedOrderTopic, order.getUserName(), order);
    }
}
