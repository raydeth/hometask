package com.github.raydeth.service.consumer;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbit")
@Slf4j
@RequiredArgsConstructor
public class RabbitMqEventConsumer implements EventConsumer {

    private final EventService service;

    @Override
    @RabbitListener(queues = {"create-event-request"})
    public void createEvent(Event event) {
        log.info("Listened create event message: " + event.getEventId());
        service.createEvent(event);
    }

    @Override
    @RabbitListener(queues = {"update-event-request"})
    public void updateEvent(Event event) {
        log.info("Listened update event message: " + event.getEventId());
        service.updateEvent(event.getEventId(), event);
    }

    @Override
    @RabbitListener(queues = {"delete-event-request"})
    public void deleteEvent(Long eventId) {
        log.info("Listened delete event message: " + eventId);
        service.deleteEvent(eventId);
    }
}
