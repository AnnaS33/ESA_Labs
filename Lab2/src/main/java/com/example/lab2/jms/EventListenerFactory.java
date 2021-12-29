package com.example.lab2.jms;

public interface EventListenerFactory {
    EventListener createEmailLoggerListener();
    EventListener createEventLoggerListener();
}
