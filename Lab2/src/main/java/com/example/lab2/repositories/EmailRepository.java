package com.example.lab2.repositories;
import com.example.lab2.models.Email;

import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Integer>{
}
