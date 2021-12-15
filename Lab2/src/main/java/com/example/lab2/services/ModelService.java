package com.example.lab2.services;

import com.example.lab2.models.Model;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

public interface ModelService {
    Optional<Model> findById(Integer id);
    List<Model> findAll();
    void save(Model model);
    void delete(Model model);
//    List<Model> findByName(String name);
//
//
}
