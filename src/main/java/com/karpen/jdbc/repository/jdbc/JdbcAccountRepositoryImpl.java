package com.karpen.jdbc.repository.jdbc;

import com.karpen.jdbc.model.Account;
import com.karpen.jdbc.model.AccountStatus;
import com.karpen.jdbc.repository.AccountRepository;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.karpen.jdbc.util.ConnectToDataBase.*;

public class JdbcAccountRepositoryImpl implements AccountRepository {

    private final ConnectToDataBase connectToDataBase = new ConnectToDataBase();

    @Override
    public Account getById(Long id) {
        String sql = "SELECT * FROM account WHERE id =" + id;
        ResultSet resultSet = null;
        Account account = new Account();
        String content = null;
        String accountStatus = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                id = resultSet.getLong(1);
                account.setId(id);
                content = resultSet.getString(2);
                account.setContent(content);
                accountStatus = resultSet.getString(3);
                account.setAccountStatus(AccountStatus.valueOf(accountStatus));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return account;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM account WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account create(Account account) {
        String content = account.getContent();
        Long id = account.getId();
        String accountStatus = String.valueOf(account.getAccountStatus());

        String sql = "INSERT INTO account (id, content, accountStatus) values (" + id + "," + "'" + content + "'" + "," + "'" + accountStatus + "'" + ")";
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public Account update(Account account) {
        String content = account.getContent();
        Long id = account.getId();
        String accountStatus = String.valueOf(account.getAccountStatus());
        String sql = "UPDATE account SET content =" + "'" + content + "'" + "," + "accountStatus = " + "'" + accountStatus + "'" + "WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";

        ResultSet resultSet = null;
        String content = null;
        Long id = null;
        String accountStatus = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                Account account = new Account();
                id = resultSet.getLong(1);
                account.setId(id);
                content = resultSet.getString(2);
                account.setContent(content);
                accountStatus = resultSet.getString(3);
                account.setAccountStatus(AccountStatus.valueOf(accountStatus));
                accounts.add(account);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return accounts;
    }

    public Long lastId() {
        Long lastId = 0L;
        String sql = "SELECT id FROM account ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                lastId = resultSet.getLong(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return lastId;
    }

}