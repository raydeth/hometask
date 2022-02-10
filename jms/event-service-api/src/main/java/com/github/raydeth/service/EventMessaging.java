package com.github.raydeth.service;

import com.github.raydeth.model.Event;

public interface EventMessaging {
    void createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long id);
}
