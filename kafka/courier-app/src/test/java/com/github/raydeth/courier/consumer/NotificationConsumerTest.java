package com.github.raydeth.courier.consumer;

import com.github.raydeth.courier.CourierAppApplicationTests;
import com.github.raydeth.kafka.model.Notification;
import com.github.raydeth.kafka.model.NotificationStatus;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class NotificationConsumerTest extends CourierAppApplicationTests {

    @Autowired
    private NotificationConsumer consumer;

    @Autowired
    private KafkaTemplate<UUID, Notification> kafkaTemplate;

    private Notification testNotification;

    @Test
    public void shouldReturnNotificationInFirstPartition() throws InterruptedException {
        testConsumerByPartition(0);
    }

    @Test
    public void shouldReturnNotificationInSecondPartition() throws InterruptedException {
        testConsumerByPartition(1);
    }

    @Test
    public void shouldReturnNotificationInThirdPartition() throws InterruptedException {
        testConsumerByPartition(2);
    }

    private void testConsumerByPartition(Integer partition) throws InterruptedException {
        UUID userId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        testNotification = new Notification(NotificationStatus.IS_READY, orderId, userId);

        ProducerRecord<UUID, Notification> producerRecord = new ProducerRecord<>("notification", partition, testNotification.getUserId(), testNotification);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, new byte[]{1, 2, 3});
        kafkaTemplate.send(producerRecord);
        kafkaTemplate.flush();

        consumer.latch.await(10000, TimeUnit.MILLISECONDS);
        assertEquals(consumer.latch.getCount(), 0L);
    }
}
