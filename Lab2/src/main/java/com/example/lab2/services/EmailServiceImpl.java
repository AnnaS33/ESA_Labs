package com.example.lab2.services;

import com.example.lab2.models.Dress;
import com.example.lab2.models.Email;
import com.example.lab2.repositories.DressRepository;
import com.example.lab2.repositories.EmailRepository;
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
public class EmailServiceImpl implements EmailService{
    private final EmailRepository repository;

    @Autowired
    public EmailServiceImpl(EmailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Email> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Email> findAll() {
        return Converter.iterableToArrayList(repository.findAll());
    }

    @Override
    public void save(Email dress) {
        repository.save(dress);
    }

    @Override
    public void delete(Email dress) {
        repository.delete(dress);

    }
}
