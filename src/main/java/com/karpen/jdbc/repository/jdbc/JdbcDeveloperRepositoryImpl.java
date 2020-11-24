package com.karpen.jdbc.repository.jdbc;


import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.repository.DeveloperRepository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.karpen.jdbc.util.ConnectToDataBase.*;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    @Override
    public Developer create(Developer developer) throws SQLException {
        String name = developer.getName();
        Long id = developer.getId();
        Long idAccount = developer.getAccountId();
        Set<Long> idSkills = developer.getSkillIds();
        Long idAccountStatus = developer.getAccountStatusId();
        for (Long idSkill : idSkills) {
            String sql = "INSERT INTO developers (id, name, id_account, id_skill, id_accountStatus) values (" + id + "," + "'" + name + "'" + idAccount + idSkill + idAccountStatus + ")";
            openStatement(connectToDB()).executeUpdate(sql);
            closeStatement(openStatement(connectToDB()));
        }
        return getById(id);
    }

    @Override
    public Developer update(Developer developer) throws SQLException {
        Long id = developer.getId();

        String sql = "UPDATE developers SET id =" + "'" + id + "'" + " WHERE id =" + id;
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));

        String name = developer.getName();
        sql = "UPDATE developers SET name =" + "'" + name + "'" + " WHERE id =" + id;
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));

        Long idAccount = developer.getAccountId();
        sql = "UPDATE developers SET id_account =" + "'" + idAccount + "'" + " WHERE id =" + id;
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));

        Set<Long> idSkills = developer.getSkillIds();
        for (Long idSkill : idSkills) {
            sql = "UPDATE developers SET id_skill =" + "'" + idSkill + "'" + " WHERE id =" + id;
            openStatement(connectToDB()).executeUpdate(sql);
            closeStatement(openStatement(connectToDB()));
        }

        Long idAccountStatus = developer.getAccountStatusId();
        sql = "UPDATE developers SET id_accountStatus =" + "'" + idAccountStatus + "'" + " WHERE id =" + id;
        openStatement(connectToDB()).executeUpdate(sql);
        closeStatement(openStatement(connectToDB()));
        return getById(id);
    }

    @Override
    public List<Developer> getAll() throws SQLException {

        Developer developer = new Developer();
        List<Developer> developers = new ArrayList<>();

        String sql = "SELECT * FROM developers";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long idAccount = resultSet.getLong("id_account");
            Set<Long> idSkills = Collections.singleton(resultSet.getLong("id_skill"));
            Long AccountStatusId = resultSet.getLong("id_accountStatus");
            developer.setId(id);
            developer.setName(name);
            developer.setAccountId(idAccount);
            developer.setSkillIds(idSkills);
            developer.setAccountStatusId(AccountStatusId);
            developers.add(developer);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return developers;
    }

    @Override
    public Developer getById(Long id) throws SQLException {
        String sql = "SELECT * FROM developers WHERE id =" + id;
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        Developer developer = new Developer();
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long idAccount = resultSet.getLong("id_account");
            Set<Long> idSkills = Collections.singleton(resultSet.getLong("id_skill"));
            Long AccountStatusId = resultSet.getLong("id_accountStatus");
            developer.setId(id);
            developer.setName(name);
            developer.setAccountId(idAccount);
            developer.setSkillIds(idSkills);
            developer.setAccountStatusId(AccountStatusId);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM developers WHERE id =" + id;
        try {
            openStatement(connectToDB()).executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeStatement(openStatement(connectToDB()));
    }

    @Override
    public Long maxId() throws SQLException {

        /*
        Long maxId = 0L;
        String sql = "SELECT max(id) FROM developers";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        while (resultSet.next()) {
            maxId = resultSet.getLong("id");
        }
        closeStatement(openStatement(connectToDB()));
        if (maxId==null) {
            return 0L;
        }
        else return maxId;
         */
        return 0L;
    }
}