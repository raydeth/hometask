package com.github.raydeth.service.impl;

import com.github.raydeth.model.Event;
import com.github.raydeth.repository.EventRepository;
import com.github.raydeth.service.EventMessaging;
import com.github.raydeth.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired(required = false)
    private EventMessaging eventMessaging;

    @Autowired
    private EventRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Event createEvent(Event event) {
        event.setEventId(null);
        Event savedEvent = repository.save(event);
        eventMessaging.createEvent(event);
        return savedEvent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Event updateEvent(Long id, Event event) {
        event.setEventId(id);
        repository.save(event);
        eventMessaging.updateEvent(event);
        return event;
    }

    @Override
    public Event getEvent(Long id) {
        return repository.findById(id).orElseThrow(() -> new NullPointerException("Can not find EVENT by id: " + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Event deleteEvent(Long id) {
        Event event = getEvent(id);
        repository.delete(event);
        eventMessaging.deleteEvent(event.getEventId());
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

}
