package com.example.lab2.services;

import com.example.lab2.jms.Sender;
import com.example.lab2.models.Model;
import com.example.lab2.repositories.DressRepository;
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
public class ModelServiceImpl implements ModelService {
    private final ModelRepository repository;
    @Autowired
    private Sender sender;

    @Autowired
    public ModelServiceImpl(ModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Model> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Model> findAll() {
        return Converter.iterableToArrayList(repository.findAll());
    }

    @Override
    public void save(Model model) {
        repository.save(model);
        sender.sendInsertEvent("Dress",model);

    }

    @Override
    public void delete(Model model) {
        repository.delete(model);
        sender.sendDeleteEvent("Dress",model);

    }
}
