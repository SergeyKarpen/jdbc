package com.karpen.jdbc.repository.jdbc;

import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.SkillRepository;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.karpen.jdbc.util.ConnectToDataBase.*;


public class JdbcSkillsRepositoryImpl implements SkillRepository {

    ConnectToDataBase connectToDataBase = new ConnectToDataBase();

    @Override
    public Skill create(Skill skill) throws SQLException {
        String name = skill.getName();
        Long id = skill.getId();
        String sql = "INSERT INTO skills (id, name) values (" + id + "," + "'" + name + "'" + ")";
        connectToDataBase.resultExecuteUpdate(sql);
        return getById(id);
    }

    @Override
    public Skill update(Skill skill) throws SQLException {
        String name = skill.getName();
        Long id = skill.getId();
        String sql = "UPDATE skills SET name =" + "'" + name + "'" + "WHERE id =" + id;
        connectToDataBase.resultExecuteUpdate(sql);
        return getById(id);
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skills";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);

        while (resultSet.next()) {
            Skill skill = new Skill();
            Long id = resultSet.getLong("id");
            skill.setId(id);
            String name = resultSet.getString("name");
            skill.setName(name);
            skills.add(skill);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return skills;
    }

    @Override
    public Skill getById(Long aLong) throws SQLException {
        String sql = "SELECT * FROM skills WHERE id =" + aLong;
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        Skill skill = new Skill();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            skill.setId(id);
            String name = resultSet.getString("name");
            skill.setName(name);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return skill;
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        String sql = "DELETE FROM skills WHERE id =" + aLong;
        connectToDataBase.resultExecuteUpdate (sql);
    }

    public Long maxId() throws SQLException {
        Long maxId = null;
        String sql = "SELECT MAX(id) FROM skills";
        ResultSet resultSet = result(openStatement(connectToDB()), sql);
        while (resultSet.next()) {
            maxId = resultSet.getLong(1);
        }
        return maxId;
    }

}
