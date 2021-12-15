package com.example.lab2.repositories;

import com.example.lab2.models.Model;
import org.springframework.data.repository.CrudRepository;


public interface ModelRepository extends CrudRepository<Model, Integer> {
}
