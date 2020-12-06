package com.karpen.jdbc.repository.jdbc;


import com.karpen.jdbc.model.Account;
import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.model.Skill;
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

    private final ConnectToDataBase connectToDataBase = new ConnectToDataBase();

    @Override
    public Developer create(Developer developer) {
        Long id = developer.getId();
        String firstName = developer.getFirstName();
        String lastName = developer.getLastName();
        Account account = developer.getAccount();
        Set<Skill> skills = developer.getSkills();
        String sql = "INSERT INTO developers (id, firstName, lastName, account) values (" + id + "," + "'" + firstName + "'" + "," + lastName + "," + "'" + account + "'" + ")";
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Skill skill : skills) {
            String sqlSkillsSet = "INSERT INTO devId_skillId (id_developers, id_skill) values (" + id + "," + "'" + skill.getId() + "'" + ")";
            try {
                connectToDataBase.resultExecuteUpdate(sqlSkillsSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return getById(id);
    }

    @Override
    public Developer update(Developer developer) {
        Long id = developer.getId();

        String sql = "UPDATE developers SET id =" + "'" + id + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String name = developer.getName();
        sql = "UPDATE developers SET name =" + "'" + name + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Long idAccount = developer.getAccountId();
        sql = "UPDATE developers SET id_account =" + "'" + idAccount + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
/*
        Set<Long> idSkills = developer.getSkillIds();
        for (Long idSkill : idSkills) {
            sql = "UPDATE developers SET id_skill =" + "'" + idSkill + "'" + " WHERE id =" + id;
            connectToDataBase.resultExecuteUpdate (sql);
        }

 */

        Long idAccountStatus = developer.getAccountStatusId();
        sql = "UPDATE developers SET id_accountStatus =" + "'" + idAccountStatus + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public List<Developer> getAll() {

        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers";
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
            Developer developer = new Developer();
            Long id = null;
            try {
                id = resultSet.getLong(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            developer.setId(id);
            String name = null;
            try {
                name = resultSet.getString(2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            developer.setName(name);
            Long idAccount = null;
            try {
                idAccount = resultSet.getLong(3);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            developer.setAccountId(idAccount);
            Long AccountStatusId = null;
            try {
                AccountStatusId = resultSet.getLong(4);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            developer.setAccountStatusId(AccountStatusId);
            developers.add(developer);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return developers;
    }
    @Override
    public Developer getById(Long id) {
        String sql = "SELECT * FROM developer WHERE id =" + id;
        ResultSet resultSet = null;
        Developer developer = new Developer();
        Long IdAccount = null;
        while (true) {
            try {
                resultSet = result(openStatement(connectToDB()), sql);
                if (!resultSet.next()) break;
                id = resultSet.getLong("id");
                developer.setId(id);
                String firstName = resultSet.getString("firstName");
                developer.setFirstName(firstName);
                String lastName = resultSet.getString("lastName");
                developer.setLastName(lastName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally {
                closeStatement(openStatement(connectToDB()));
                assert resultSet != null;
                closeResult(resultSet);
            }
            String sqlAccountContent = "SELECT * FROM developerId_accountId  WHERE id =" + id;
            try {
                resultSet = result(openStatement(connectToDB()), sqlAccountContent);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    IdAccount = resultSet.getLong("id_account");
                  } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                finally {
                    closeStatement(openStatement(connectToDB()));
                    assert resultSet != null;
                    closeResult(resultSet);
                }

                String sqlAccountContentById = "SELECT * FROM account WHERE id =" + IdAccount;
                try {
                    resultSet = result(openStatement(connectToDB()), sqlAccountContentById);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String accountContent = resultSet.getString("content");
                developer.setAccount();
            }
        }


        String sql2 = "SELECT id_skill FROM devId_skillId WHERE id_developers =" + developer.getId();
        Set<Long> idSkills = new HashSet<>();
        ResultSet resultSet2 = null;
        try {
            resultSet2 = result(openStatement(connectToDB()), sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet2.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Long idSkill = null;
            try {
                idSkill = resultSet2.getLong("id_skill");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            idSkills.add(idSkill);
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        developer.setSkillIds(idSkills);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM developers WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Long maxId() {
        Long maxId = null;
        String sql = "SELECT MAX(id) FROM developers";
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
                maxId = resultSet.getLong(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        return maxId;
    }
}
