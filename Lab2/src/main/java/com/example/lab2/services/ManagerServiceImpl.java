package com.example.lab2.services;

import com.example.lab2.jms.EventManager;
import com.example.lab2.models.Manager;
import com.example.lab2.repositories.ManagerRepository;
import com.example.lab2.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.util.List;
import java.util.Optional;

@Service
@Repository
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository repository;

    private EventManager eventManager;

    @Autowired
    public ManagerServiceImpl(ManagerRepository repository, JmsTemplate template) {
        this.repository = repository;
        try {
            eventManager = new EventManager(template);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Manager> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Manager> findAll() {
        return Converter.iterableToArrayList(repository.findAll());
    }

    @Override
    public void save(Manager manager) {
        repository.save(manager);
        eventManager.sendInsertEvent("Manager",manager);

    }

    @Override
    public void delete(Manager manager) {
        repository.delete(manager);
        eventManager.sendDeleteEvent("Manager",manager);


    }
}
