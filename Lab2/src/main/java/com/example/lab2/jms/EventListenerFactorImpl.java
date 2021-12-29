package com.example.lab2.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventListenerFactorImpl implements EventListenerFactory{

    @Autowired
    private EmailLoggerListener emailLoggerListener;

    @Autowired
    private EventLoggerListener eventLoggerListener;

    @Override
    public EventListener createEmailLoggerListener() {
        return emailLoggerListener;
    }

    @Override
    public EventListener createEventLoggerListener() {
        return eventLoggerListener;
    }
}
