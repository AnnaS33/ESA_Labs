package com.example.lab2.services;


import com.example.lab2.jms.EventListenerFactory;
import com.example.lab2.jms.EventManager;
import com.example.lab2.models.Dress;
import com.example.lab2.repositories.DressRepository;
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
public class DressServiceImpl implements DressService {
    private final DressRepository repository;

    private EventManager eventManager;

    @Autowired
    public DressServiceImpl(DressRepository repository, EventListenerFactory factory, EventManager topic) {
        this.repository = repository;
        eventManager = topic;
        eventManager.subscribe(factory.createEmailLoggerListener());
        eventManager.subscribe(factory.createEventLoggerListener());
    }

    @Override
    public Optional<Dress> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Dress> findAll() {
        return Converter.iterableToArrayList(repository.findAll());
    }

    @Override
    public void save(Dress dress) {
        repository.save(dress);
        eventManager.sendInsertEvent("Dress",dress);
    }

    @Override
    public void delete(Dress dress) {
        repository.delete(dress);
        eventManager.sendDeleteEvent("Dress",dress);

    }
}
