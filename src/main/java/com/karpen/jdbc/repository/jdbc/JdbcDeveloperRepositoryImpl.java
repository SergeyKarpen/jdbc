package com.karpen.jdbc.repository.jdbc;


import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.repository.DeveloperRepository;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.karpen.jdbc.util.ConnectToDataBase.*;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    ConnectToDataBase connectToDataBase = new ConnectToDataBase();

    @Override
    public Developer create(Developer developer) throws SQLException {
        String name = developer.getName();
        Long id = developer.getId();
        Long idAccount = developer.getAccountId();
        Long idAccountStatus = developer.getAccountStatusId();
        String sql = "INSERT INTO developers (id, name, id_account, id_accountStatus) values (" + id + "," + "'" + name + "'" + "," + idAccount + "," + idAccountStatus + ")";
        connectToDataBase.resultExecuteUpdate(sql);

        Set<Long> idSkills = developer.getSkillIds();
        for (Long idSkill : idSkills) {
            String sqlSkillsSet = "INSERT INTO devId_skillId (id_developers, id_skill) values (" + id + "," + idSkill + ")";
            connectToDataBase.resultExecuteUpdate(sqlSkillsSet);
        }
        return getById(id);
    }

    @Override
    public Developer update(Developer developer) throws SQLException {
        Long id = developer.getId();

        String sql = "UPDATE developers SET id =" + "'" + id + "'" + " WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);

        String name = developer.getName();
        sql = "UPDATE developers SET name =" + "'" + name + "'" + " WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);

        Long idAccount = developer.getAccountId();
        sql = "UPDATE developers SET id_account =" + "'" + idAccount + "'" + " WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);
/*
        Set<Long> idSkills = developer.getSkillIds();
        for (Long idSkill : idSkills) {
            sql = "UPDATE developers SET id_skill =" + "'" + idSkill + "'" + " WHERE id =" + id;
            connectToDataBase.resultExecuteUpdate (sql);
        }

 */

        Long idAccountStatus = developer.getAccountStatusId();
        sql = "UPDATE developers SET id_accountStatus =" + "'" + idAccountStatus + "'" + " WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);
        return getById(id);
    }

    @Override
    public List<Developer> getAll() throws SQLException {

        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);

        while (resultSet.next()) {
            Developer developer = new Developer();
            Long id = resultSet.getLong(1);
            developer.setId(id);
            String name = resultSet.getString(2);
            developer.setName(name);
            Long idAccount = resultSet.getLong(3);
            developer.setAccountId(idAccount);
            Long AccountStatusId = resultSet.getLong(4);
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
            Long AccountStatusId = resultSet.getLong("id_accountStatus");
            developer.setId(id);
            developer.setName(name);
            developer.setAccountId(idAccount);
            developer.setAccountStatusId(AccountStatusId);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);

        String sql2 = "SELECT id_skill FROM devId_skillId WHERE id_developers =" + developer.getId();
        Set<Long> idSkills = new HashSet<>();
        ResultSet resultSet2 = result(openStatement(connectToDB()), sql2);
        while (resultSet2.next()) {
          Long idSkill = resultSet2.getLong("id_skill");
            idSkills.add(idSkill);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        developer.setSkillIds(idSkills);
        return developer;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM developers WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);
    }

    @Override
    public Long maxId() throws SQLException {
        Long maxId = null;
        String sql = "SELECT MAX(id) FROM developers";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        while (resultSet.next()) {
            maxId = resultSet.getLong(1);
        }
        closeStatement(openStatement(connectToDB()));
        return maxId;
    }
}
