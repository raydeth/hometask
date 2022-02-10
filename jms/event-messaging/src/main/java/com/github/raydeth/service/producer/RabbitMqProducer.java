package com.github.raydeth.service.producer;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("rabbit")
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer implements EventMessaging {

    @Value("${jms.event.topic.notification.name}")
    private String notificationExchange;

    @Value("${jms.event.topic.notification.create}")
    private String createNotificationQueueName;

    @Value("${jms.event.topic.notification.update}")
    private String updateNotificationQueueName;

    @Value("${jms.event.topic.notification.delete}")
    private String deleteNotificationQueueName;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void createEvent(Event event) {
        log.info("Created event with key: " + event.getEventId());
        rabbitTemplate.convertAndSend(notificationExchange, createNotificationQueueName, event);
    }

    @Override
    public void updateEvent(Event event) {
        log.info("Updated event with key: " + event.getEventId());
        rabbitTemplate.convertAndSend(notificationExchange, updateNotificationQueueName, event);
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleted event with key: " + id);
        rabbitTemplate.convertAndSend(notificationExchange, deleteNotificationQueueName, id);
    }
}
