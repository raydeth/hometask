package com.github.raydeth.service.consumer;

import com.github.raydeth.model.Event;
import org.hibernate.boot.model.naming.Identifier;

public interface EventConsumer {
    void createEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Identifier identifier);
}
