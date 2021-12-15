package com.example.lab2.services;

import com.example.lab2.models.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Optional<Event> findById(Integer id);
    List<Event> findAll();
    void save(Event event);
    void delete(Event event);
}
