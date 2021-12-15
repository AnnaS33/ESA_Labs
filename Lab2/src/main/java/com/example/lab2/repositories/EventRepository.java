package com.example.lab2.repositories;

import com.example.lab2.models.Event;
import org.springframework.data.repository.CrudRepository;


public interface EventRepository extends CrudRepository<Event, Integer>{
}
