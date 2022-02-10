package com.github.raydeth.service.producer;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;

@Component
@Profile("kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer implements EventMessaging {

    @Value("${jms.event.topic.notification.create}")
    private String notificationCreateTopic;

    @Value("${jms.event.topic.notification.update}")
    private String notificationUpdateTopic;

    @Value("${jms.event.topic.notification.delete}")
    private String notificationDeleteTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void createEvent(Event event) {
        ListenableFuture<SendResult<String, Object>> sentMessage = kafkaTemplate.send(notificationCreateTopic, String.valueOf(event.hashCode()), event);
        sentMessage.addCallback(result -> log.info("Created event with key: " + Objects.requireNonNull(result).getProducerRecord().key()),
                ex -> log.error("Exception while create: ", ex));
    }

    @Override
    public void updateEvent(Event event) {
        ListenableFuture<SendResult<String, Object>> sentMessage = kafkaTemplate.send(notificationUpdateTopic, String.valueOf(event.hashCode()), event);
        sentMessage.addCallback(result -> log.info("Updated event with key: " + Objects.requireNonNull(result).getProducerRecord().key()),
                ex -> log.error("Exception while update: ", ex));
    }

    @Override
    public void deleteEvent(Long id) {
        String key = String.valueOf(id);
        ListenableFuture<SendResult<String, Object>> sentMessage = kafkaTemplate.send(notificationDeleteTopic, key, key);
        sentMessage.addCallback(result -> log.info("Deleted event with key: " + Objects.requireNonNull(result).getProducerRecord().key()),
                ex -> log.error("Exception while delete: ", ex));
    }
}
