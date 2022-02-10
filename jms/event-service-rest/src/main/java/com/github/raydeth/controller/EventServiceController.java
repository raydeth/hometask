package com.github.raydeth.controller;

import com.github.raydeth.model.Event;
import com.github.raydeth.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
@AllArgsConstructor
public class EventServiceController {

    private final EventService service;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Event getEvent(@PathVariable("id") Long id) {
        return service.getEvent(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Event updateEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        return service.updateEvent(id, event);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public Event DeleteEvent(@PathVariable("id") Long id) {
        return service.deleteEvent(id);
    }

}
