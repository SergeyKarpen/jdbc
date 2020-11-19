package com.karpen.jdbc.repository.io;

import com.karpen.jdbc.model.Account;

import java.io.IOException;
import java.util.List;

public class JsonAccountRepositoryImpl implements com.karpen.jdbc.repository.AccountRepository {

    private final static String relativePathToFile = "src\\main\\resources\\accounts.json";


    @Override
    public Account getById(Long aLong) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws IOException {

    }

    @Override
    public List<Account> getListObjectsFromJson(String s) throws IOException {
        return null;
    }

    @Override
    public void writeListObjectsInJson(List<Account> list, String s) throws IOException {

    }

    @Override
    public Long maxIdInList(List<Account> t) throws IOException {
        return null;
    }

    @Override
    public Account create(Account account) throws IOException {
        return null;
    }

    @Override
    public Account update(Account account) throws IOException {
        return null;
    }

    @Override
    public void getAll() throws IOException {

    }
}
