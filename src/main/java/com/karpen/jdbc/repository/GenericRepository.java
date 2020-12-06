package com.karpen.jdbc.repository;


import java.util.List;

public interface GenericRepository<T, ID> {

    T create(T t);

    T update(T t);

    List<T> getAll();

    T getById(ID id);

    void deleteById(ID id);

   // ID maxId();
}
