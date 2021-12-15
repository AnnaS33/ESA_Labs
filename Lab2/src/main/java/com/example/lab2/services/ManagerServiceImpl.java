package com.example.lab2.services;

import com.example.lab2.jms.Sender;
import com.example.lab2.models.Manager;
import com.example.lab2.repositories.ManagerRepository;
import com.example.lab2.repositories.ModelRepository;
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
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository repository;
    @Autowired
    private Sender sender;

    @Autowired
    public ManagerServiceImpl(ManagerRepository repository) {
        this.repository = repository;
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
        sender.sendInsertEvent("Manager",manager);

    }

    @Override
    public void delete(Manager manager) {
        repository.delete(manager);
        sender.sendDeleteEvent("Manager",manager);


    }
}
