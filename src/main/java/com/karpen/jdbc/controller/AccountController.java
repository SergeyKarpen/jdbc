package com.karpen.jdbc.controller;

import com.karpen.jdbc.model.Account;
import com.karpen.jdbc.repository.jdbc.JdbcAccountRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountController {
    private final JdbcAccountRepositoryImpl accountRepository = new JdbcAccountRepositoryImpl();


    public Account create(String name) throws IOException, SQLException {
        Account account = new Account();
        account.setName(name);
        account.setId(accountRepository.maxId());
        return accountRepository.create(account);
    }

    public Account update(Long id, String name) throws IOException, SQLException {
        Account account = new Account();
        account.setName(name);
        account.setId(id);
        return accountRepository.update(account);
    }

    public Account getById(Long id) throws IOException, SQLException {
        return accountRepository.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        accountRepository.deleteById(id);
    }

    public List<Account> getAll() throws IOException, SQLException {
        return accountRepository.getAll();
    }
}

