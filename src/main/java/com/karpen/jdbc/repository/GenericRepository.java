package com.karpen.jdbc.repository;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t) throws IOException;

    T update(T t) throws IOException;

    void getAll() throws IOException, SQLException;

    T getById(ID id) throws IOException, SQLException;

    void deleteById(ID id) throws IOException;

    List<T> getListObjectsFromJson(String s) throws IOException;

    void writeListObjectsInJson(List<T> list, String s) throws IOException;

    ID maxIdInList(List<T> t) throws IOException;
}
