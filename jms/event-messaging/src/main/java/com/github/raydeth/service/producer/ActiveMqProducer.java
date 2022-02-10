package com.github.raydeth.service.producer;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
@RequiredArgsConstructor
@Slf4j
public class ActiveMqProducer implements EventMessaging {

    @Value("${jms.event.topic.notification.create}")
    private String notificationCreateTopic;

    @Value("${jms.event.topic.notification.update}")
    private String notificationUpdateTopic;

    @Value("${jms.event.topic.notification.delete}")
    private String notificationDeleteTopic;

    private final JmsTemplate jmsTemplate;

    @Override
    public void createEvent(Event event) {
        jmsTemplate.send(notificationCreateTopic, session -> session.createObjectMessage(event));
        log.info("Created event with key: " + event.getEventId());
    }

    @Override
    public void updateEvent(Event event) {
        jmsTemplate.send(notificationUpdateTopic, session -> session.createObjectMessage(event));
        log.info("Updated event with key: " + event.getEventId());
    }

    @Override
    public void deleteEvent(Long id) {
        jmsTemplate.send(notificationDeleteTopic, session -> session.createObjectMessage(id));
        log.info("Deleted event with key: " + id);
    }
}
