package com.github.raydeth.courier.consumer;

import com.github.raydeth.kafka.model.Notification;
import com.github.raydeth.kafka.model.NotificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final KafkaTemplate kafkaTemplate;

    public final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"0"})
    })
    public void consumeFirstOrder(@Payload Notification notification,
                                  @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(notification, correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"1"})
    })
    public void consumeSecondOrder(@Payload Notification notification,
                                   @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(notification, correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"2"})
    })
    public void consumeThirdOrder(@Payload Notification notification,
                                  @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        sendToNotification(notification, correlationId);
    }

    private void sendToNotification(@Payload Notification notification,
                                    @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        if (notification.getStatus().equals(NotificationStatus.IS_READY)) {
            notification.setStatus(NotificationStatus.DELIVERED);
            ProducerRecord<UUID, Notification> producerRecord = new ProducerRecord<>("notification", notification.getUserId(), notification);
            producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, correlationId);
            kafkaTemplate.send(producerRecord);
        }
        this.latch.countDown();
    }
}
