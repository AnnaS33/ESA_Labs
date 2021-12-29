package com.example.lab2.jms;

import com.example.lab2.models.Event;
import com.example.lab2.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventLoggerListener implements EventListener{
    @Autowired
    private EventService eventService;

    @Override
    public void update(Event event) {
        eventService.save(event);
    }
}
