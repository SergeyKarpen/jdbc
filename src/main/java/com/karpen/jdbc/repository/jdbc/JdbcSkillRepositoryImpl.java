package com.karpen.jdbc.repository.jdbc;

import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.SkillRepository;
import com.karpen.jdbc.util.ConnectToDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.karpen.jdbc.util.ConnectToDataBase.*;


public class JdbcSkillRepositoryImpl implements SkillRepository {

    private final ConnectToDataBase connectToDataBase = new ConnectToDataBase();

    @Override
    public Skill create(Skill skill) {
        String name = skill.getName();
        Long id = skill.getId();
        String sql = "INSERT INTO skill (id, name) values (" + id + "," + "'" + name + "'" + ")";
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        String name = skill.getName();
        Long id = skill.getId();
        String sql = "UPDATE skill SET name =" + "'" + name + "'" + "WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skill";

        ResultSet resultSet = null;
        String name = null;
        Long id = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Skill skill = new Skill();
                id = resultSet.getLong(1);
                name = resultSet.getString(2);
                skill.setId(id);
                skill.setName(name);
                skills.add(skill);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return skills;
    }

    @Override
    public Skill getById(Long id) {
        String sql = "SELECT * FROM skill WHERE id =" + id;
        Skill skill = new Skill();
        skill.setId(id);
        ResultSet resultSet = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                String name = resultSet.getString("name");
                skill.setName(name);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return skill;
    }

    @Override
    public void deleteById(Long aLong) {
        String sql = "DELETE FROM skill WHERE id =" + aLong;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Long maxId() {
        Long maxId = 0L;
        String sql = "SELECT MAX(id) FROM skill";
        ResultSet resultSet = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                assert resultSet != null;
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                maxId = resultSet.getLong(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return maxId + 1;
    }
}
