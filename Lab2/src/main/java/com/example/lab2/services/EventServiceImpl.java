package com.example.lab2.services;


import com.example.lab2.models.Event;
import com.example.lab2.repositories.EventRepository;
import com.example.lab2.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Repository
@Transactional
public class EventServiceImpl implements EventService {
    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return Converter.iterableToArrayList(repository.findAll());
    }

    @Override
    public void save(Event dress) {
        repository.save(dress);
    }

    @Override
    public void delete(Event event) {
        repository.delete(event);

    }
}
