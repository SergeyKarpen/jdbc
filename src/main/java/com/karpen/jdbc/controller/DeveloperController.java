package com.karpen.jdbc.controller;

import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.jdbc.JdbcAccountRepositoryImpl;
import com.karpen.jdbc.repository.jdbc.JdbcDeveloperRepositoryImpl;
import com.karpen.jdbc.repository.jdbc.JdbcSkillRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperController {

    private final JdbcDeveloperRepositoryImpl jdbcDeveloperRepository = new JdbcDeveloperRepositoryImpl();
    private final JdbcAccountRepositoryImpl jdbcAccountRepository = new JdbcAccountRepositoryImpl();
    private final JdbcSkillRepositoryImpl jdbcSkillRepository = new JdbcSkillRepositoryImpl();

    public Developer create(String firstName, String lastName, Long id_account, Set<Long> skillId) throws IOException, SQLException {
        Developer developer = new Developer();
        Set<Skill> skills = new HashSet<>();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setId(jdbcDeveloperRepository.lastId() + 1);
        developer.setAccount(jdbcAccountRepository.getById(id_account));
        for (Long aLong : skillId) {
            skills.add(jdbcSkillRepository.getById(aLong));
        }
        developer.setSkills(skills);
        return jdbcDeveloperRepository.create(developer);
    }

    public Developer update(String firstname, String lastName, Long id, Long id_account, Set<Long> skillId) throws IOException, SQLException {
        Developer developer = new Developer();
        Set<Skill> skills = new HashSet<>();
        developer.setFirstName(firstname);
        developer.setLastName(lastName);
        developer.setId(id);
        developer.setAccount(jdbcAccountRepository.getById(id_account));
        for (Long aLong : skillId) {
            skills.add(jdbcSkillRepository.getById(aLong));
        }
        developer.setSkills(skills);
        return jdbcDeveloperRepository.update(developer);
    }

    public Developer getById(Long id) throws IOException, SQLException {
        return jdbcDeveloperRepository.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        jdbcDeveloperRepository.deleteById(id);
    }

    public List<Developer> getAll() throws IOException, SQLException {
        return jdbcDeveloperRepository.getAll();
    }
}
