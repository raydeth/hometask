package com.github.raydeth.palmetto.app.consumer;

import com.github.raydeth.kafka.model.Notification;
import com.github.raydeth.kafka.model.NotificationStatus;
import com.github.raydeth.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "order", partitions = {"0"})
    })
    public void consumePizzaOrder(@Payload Order order,
                                  @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(order, correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "order", partitions = {"1"})
    })
    public void consumeRollOrder(@Payload Order order,
                                 @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(order, correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "order", partitions = {"2"})
    })
    public void consumeDonerOrder(@Payload Order order,
                                  @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(order, correlationId);
    }

    private void sendToNotification(@Payload Order order,
                                    @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        ProducerRecord<String, Notification> producerRecord = new ProducerRecord<>("notification", order.getUserId().toString(),
                new Notification(NotificationStatus.IS_READY, order.getId(), order.getUserId()));
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, correlationId);
        kafkaTemplate.send(producerRecord);
    }
}
