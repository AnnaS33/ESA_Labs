package com.example.lab2.services;

import com.example.lab2.models.Manager;
import com.example.lab2.models.Model;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Optional<Manager> findById(Integer id);
    List<Manager> findAll();
    void save(Manager manager);
    void delete(Manager manager);
}
