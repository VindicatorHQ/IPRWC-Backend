package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.exception.NotFound;

import java.util.List;

public interface DAO<T> {

    T getById(String id) throws NotFound;
    T saveToDatabase(T t);
    T update(String id, T t) throws NotFound;
    void delete(String id) throws NotFound;
    List<T> getAll();
}
