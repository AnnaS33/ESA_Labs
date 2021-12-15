package com.example.lab1.DAO;

import java.util.List;

public interface Dao<T> {
    T get(Integer id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);

}
