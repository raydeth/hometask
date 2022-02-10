package com.github.raydeth.service.consumer;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
@Slf4j
@RequiredArgsConstructor
public class KafkaEventConsumer implements EventConsumer {

    private final EventService service;

    @Override
    @KafkaListener(topics = {"create-event-request"}, containerFactory = "defaultEventRequestContainerFactory")
    public void createEvent(Event event) {
        log.info("Listened create event message: " + event.getEventId());
        service.createEvent(event);
    }

    @Override
    @KafkaListener(topics = {"update-event-request"}, containerFactory = "defaultEventRequestContainerFactory")
    public void updateEvent(Event event) {
        log.info("Listened update event message: " + event.getEventId());
        service.updateEvent(event.getEventId(), event);
    }

    @Override
    @KafkaListener(topics = {"delete-event-request"}, containerFactory = "deleteEventRequestContainerFactory")
    public void deleteEvent(Identifier identifier) {
        Long eventId = Long.valueOf(identifier.getText());
        log.info("Listened delete event message: " + eventId);
        service.deleteEvent(eventId);
    }
}
