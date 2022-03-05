package com.github.raydeth.kafka.clientapp.consumer;

import com.github.raydeth.kafka.clientapp.service.OrderService;
import com.github.raydeth.kafka.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    public final CountDownLatch latch = new CountDownLatch(1);

    private final OrderService service;

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"0"})
    })
    public void consumeFirstNotification(@Payload Notification notification,
                                         @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        updateOrderState(notification, "Consumed consumeOnTheWayOrder: ", correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"1"})
    })
    public void consumeSecondNotification(@Payload Notification notification,
                                          @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        updateOrderState(notification, "Consumed consumeDoneOrder: ", correlationId);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "notification", partitions = {"2"})
    })
    public void consumeThirdNotification(@Payload Notification notification,
                                         @Header(KafkaHeaders.CORRELATION_ID) byte[] correlationId) {
        updateOrderState(notification, "Consumed consumeDoneOrder: ", correlationId);
    }

    private void updateOrderState(Notification notification, String methodName, byte[] correlationId) {
        service.updateState(notification.getOrderId(), notification.getStatus().getOrderState());
        log.info(methodName + getGuidFromByteArray(correlationId));
        latch.countDown();
    }

    public static UUID getGuidFromByteArray(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long high = bb.getLong();
        long low = bb.getLong();

        return new UUID(high, low);
    }
}
