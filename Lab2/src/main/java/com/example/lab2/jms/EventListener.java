package com.example.lab2.jms;

import com.example.lab2.models.Event;

public interface EventListener {
    void update(Event event);
}
