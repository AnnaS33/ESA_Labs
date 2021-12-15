package com.example.lab2.services;

import com.example.lab2.models.Dress;
import com.example.lab2.models.Manager;

import java.util.List;
import java.util.Optional;

public interface DressService {
    Optional<Dress> findById(Integer id);
    List<Dress> findAll();
    void save(Dress dress);
    void delete(Dress dress);
}
