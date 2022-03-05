package com.github.raydeth.kafka.clientapp.consumer;

import com.github.raydeth.kafka.clientapp.ClientAppApplicationTests;
import com.github.raydeth.kafka.model.Notification;
import com.github.raydeth.kafka.model.NotificationStatus;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.test.context.jdbc.Sql;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, statements = {"insert into orders (id) values ('e1b87c2a4f084ba0906dfc2ceeee0d12')"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = {"delete from orders where id = 'e1b87c2a4f084ba0906dfc2ceeee0d12'"})
public class NotificationConsumerTest extends ClientAppApplicationTests {

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
        UUID orderId = UUID.fromString("e1b87c2a-4f08-4ba0-906d-fc2ceeee0d12");
        testNotification = new Notification(NotificationStatus.DELIVERED, orderId, userId);

        ProducerRecord<UUID, Notification> producerRecord = new ProducerRecord<>("notification", partition, testNotification.getUserId(), testNotification);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(producerRecord);
        kafkaTemplate.flush();

        consumer.latch.await(10000, TimeUnit.MILLISECONDS);
        assertEquals(consumer.latch.getCount(), 0L);
    }
}
