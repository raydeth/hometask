package com.github.raydeth.service.consumer;


import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("activemq")
@Slf4j
@RequiredArgsConstructor
public class ActiveMqEventConsumer implements EventConsumer {

    private final EventService service;

    @Override
    @JmsListener(destination = "create-event-request", containerFactory = "defaultListenerContainerFactory")
    public void createEvent(Event event) {
        log.info("Listened create event message: " + event.getEventId());
        service.createEvent(event);
    }

    @Override
    @JmsListener(destination = "update-event-request", containerFactory = "defaultListenerContainerFactory")
    public void updateEvent(Event event) {
        log.info("Listened update event message: " + event.getEventId());
        service.updateEvent(event.getEventId(), event);
    }

    @Override
    @JmsListener(destination = "delete-event-request", containerFactory = "defaultListenerContainerFactory")
    public void deleteEvent(Long eventId) {
        log.info("Listened delete event message: " + eventId);
        service.deleteEvent(eventId);
    }
}
