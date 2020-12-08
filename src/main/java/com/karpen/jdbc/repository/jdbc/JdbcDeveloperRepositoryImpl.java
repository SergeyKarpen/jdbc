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
    private final JdbcAccountRepositoryImpl jdbcAccountRepository = new JdbcAccountRepositoryImpl();
    private final JdbcSkillRepositoryImpl jdbcSkillRepository = new JdbcSkillRepositoryImpl();

    @Override
    public Developer create(Developer developer) {
        Long id = developer.getId();
        String firstName = developer.getFirstName();
        String lastName = developer.getLastName();
        Set<Skill> skills = developer.getSkills();
        Account account = developer.getAccount();
        Long idAccount = account.getId();
        String sqlDeveloper = "INSERT INTO developer (id, firstName, lastName) values (" + id + "," + "'" + firstName + "'" + "," + "'" + lastName + "'" + ")";
        try {
            connectToDataBase.resultExecuteUpdate(sqlDeveloper);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Long idSkill=null;


        for (Skill skill : skills) {
            idSkill = skill.getId();
            String sqlSkillsSet = "INSERT INTO developer_skills (developer_id, skill_id) values (" + id + "," + idSkill + ")";
            if (idSkill == null) {
                try {
                    connectToDataBase.resultExecuteUpdate(sqlSkillsSet);
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                connectToDataBase.resultExecuteUpdate(sqlSkillsSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



        String sqlAccountDeveloper = "UPDATE account SET developer_id =" + id + " WHERE id =" + idAccount;
        try {
            connectToDataBase.resultExecuteUpdate(sqlAccountDeveloper);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public Developer update(Developer developer) {
        Long id = developer.getId();

        String sql = "UPDATE developer SET id =" + id + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String firstName = developer.getFirstName();
        sql = "UPDATE developer SET name =" + "'" + firstName + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String lastName = developer.getFirstName();
        sql = "UPDATE developer SET name =" + "'" + lastName + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Account account = developer.getAccount();
        String accountContent = account.getContent();
        sql = "UPDATE developer SET account_content =" + "'" + accountContent + "'" + " WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Set<Skill> idSkills = developer.getSkills();
        for (Skill skill : idSkills) {
            Long idSkill = skill.getId();
            sql = "UPDATE developer_skills SET skill_id =" + idSkill + " WHERE id =" + id;
            try {
                connectToDataBase.resultExecuteUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return getById(id);
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developer";
        ResultSet resultSet = null;
        try {
            resultSet = result(openStatement(connectToDB()), sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                Long idDeveloper = resultSet.getLong(1);
                developers.add(getById(idDeveloper));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);
        return developers;
    }

    @Override
    public Developer getById(Long id) {
        String sql = "SELECT * FROM developer WHERE id =" + id;
        Developer developer = new Developer();
        Account account = new Account();
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        while (true) {
            try {
                resultSet = result(openStatement(connectToDB()), sql);
                if (!resultSet.next()) break;
                id = resultSet.getLong(1);
                developer.setId(id);
                String firstName = resultSet.getString(2);
                developer.setFirstName(firstName);
                String lastName = resultSet.getString(3);
                developer.setLastName(lastName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet);

        String sqlAccountContent = "SELECT id FROM account WHERE developer_id =" + id;
        while (true) {
            try {
                resultSet1 = result(openStatement(connectToDB()), sqlAccountContent);
                if (!resultSet1.next()) break;
                Long idAccount = resultSet1.getLong(1);
                developer.setAccount(jdbcAccountRepository.getById(idAccount));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet1);

        String sqlSkillsDeveloper = "SELECT skill_id FROM developer_skills WHERE developer_id =" + id;
        Set<Long> idSkills = new HashSet<>();
        try {
            resultSet2 = result(openStatement(connectToDB()), sqlSkillsDeveloper);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while (true) {
            try {
                if (!resultSet2.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Long idSkill = resultSet2.getLong("skill_id");
                idSkills.add(idSkill);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        closeStatement(openStatement(connectToDB()));
        closeResult(resultSet2);

        Set<Skill> skills = new HashSet<>();
        for (Long skillId : idSkills) {
            skills.add(jdbcSkillRepository.getById(skillId));
        }

        developer.setSkills(skills);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM developer WHERE id =" + id;
        try {
            connectToDataBase.resultExecuteUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Long lastId() {
        Long lastId = 0L;
        String sql = "SELECT id FROM DEVELOPER ORDER BY id DESC LIMIT 1";
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
