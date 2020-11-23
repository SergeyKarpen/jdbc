package com.karpen.jdbc.repository.jdbc;

import com.karpen.jdbc.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.karpen.jdbc.util.ConnectToDataBase.*;

public class JdbcAccountsRepositoryImpl implements com.karpen.jdbc.repository.AccountRepository {

    @Override
    public Account getById(Long id) throws SQLException {
        String sql = "SELECT * FROM skills WHERE id =" + id;
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        Account account = null;
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            account.setId(id);
            String name = resultSet.getString("name");
            account.setName(name);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return account;
    }

    @Override
    public void deleteById(Long aLong) {
        String sql = "DELETE FROM accounts WHERE id =" + aLong;
        try {
            openStatement(connectToDB()).executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeStatement(openStatement(connectToDB()));
    }

    @Override
    public Account create(Account account) throws SQLException {
        String name = account.getName();
        Long id = account.getId();
        String sql = "INSERT INTO accounts (id, name) values (" + id + "," + "'" + name + "'" + ")";
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));
        return getById(id);
    }

    @Override
    public Account update(Account account) throws SQLException {
        String name = account.getName();
        Long id = account.getId();
        String sql = "UPDATE accounts SET name =" + "'" + name + "'" + "WHERE id =" + id;
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));
        return getById(id);
    }

    @Override
    public List<Account> getAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        while (resultSet.next()) {
            Account account = new Account();
            Long id = resultSet.getLong("id");
            account.setId(id);
            String name = resultSet.getString("name");
            account.setName(name);
            accounts.add(account);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return accounts;
    }
}