package com.karpen.jdbc.controller;

import com.karpen.jdbc.repository.jdbc.JdbcAccountsRepositoryImpl;

public class AccountController {
    com.karpen.jdbc.repository.AccountRepository accountRepository = new JdbcAccountsRepositoryImpl();

    public AccountController() {
        this.accountRepository = accountRepository;
    }
/*
    public Account getById(Long id) throws IOException {
        return accountRepository.getById(id);
    }
    public List<Account> getAll() throws IOException {
        return accountRepository.getAll();
    }

 */
}

