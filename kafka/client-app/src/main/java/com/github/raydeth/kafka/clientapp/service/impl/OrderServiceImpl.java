package com.github.raydeth.kafka.clientapp.service.impl;

import com.github.raydeth.kafka.clientapp.repository.OrderRepository;
import com.github.raydeth.kafka.clientapp.service.OrderService;
import com.github.raydeth.kafka.model.Order;
import com.github.raydeth.kafka.model.OrderState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.order}")
    private String orderTopic;

    @Override
    public Order create(Order order) {
        Order savedOrder = repository.save(order);
        ProducerRecord<UUID, Order> orderProducerRecord = new ProducerRecord<>(orderTopic, order.getProductId(), savedOrder);

        UUID correlationId = UUID.randomUUID();

        log.info("Correlation id: " + correlationId);
        orderProducerRecord.headers().add(KafkaHeaders.CORRELATION_ID, transformUuidToByteArray(correlationId));
        kafkaTemplate.send(orderProducerRecord);
        return savedOrder;
    }

    @Override
    public Order createNewOrder(Order order) {
        order.setState(OrderState.NEW);
        return create(order);
    }

    @Override
    public Order updateState(UUID orderId, OrderState state) {
        Order order = getById(orderId);
        order.setState(state);
        repository.save(order);
        return order;
    }

    @Override
    public Order getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    private byte[] transformUuidToByteArray(UUID id) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(id.getMostSignificantBits());
        bb.putLong(id.getLeastSignificantBits());
        return bb.array();
    }
}
