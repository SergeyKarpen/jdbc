package com.karpen.jdbc.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenericService<T, ID> {

    T create(T t) throws IOException, SQLException;

    T update(T t) throws IOException, SQLException;

    List<T> getAll() throws IOException, SQLException;

    T getById(ID id) throws IOException, SQLException;

    void deleteById(ID id) throws IOException, SQLException;

    ID maxId() throws IOException, SQLException;
}
