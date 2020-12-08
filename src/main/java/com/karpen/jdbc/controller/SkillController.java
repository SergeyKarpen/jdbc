package com.karpen.jdbc.controller;

import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.jdbc.JdbcSkillRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SkillController {

    private final JdbcSkillRepositoryImpl skillRepository = new JdbcSkillRepositoryImpl();

    public void create(String name) throws IOException, SQLException {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setId(skillRepository.maxId() + 1);
        skillRepository.create(skill);
    }

    public Skill update(Long id, String name) throws IOException, SQLException {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setId(id);
        return skillRepository.update(skill);
    }

    public Skill getById(Long id) throws IOException, SQLException {
        return skillRepository.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        skillRepository.deleteById(id);
    }

    public List<Skill> getAll() throws IOException, SQLException {
        return skillRepository.getAll();
    }


}
