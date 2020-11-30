package com.karpen.jdbc.controller;

import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.repository.DeveloperRepository;
import com.karpen.jdbc.service.serviceImpl.DeveloperServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class DeveloperController {

    private DeveloperRepository developerRepository = new DeveloperServiceImpl();

    public Developer create(String name, Long id_account, Set<Long> skillId, Long id_accountStatus) throws IOException, SQLException {
        Developer developer = new Developer();
        developer.setName(name);
        developer.setId(developerRepository.maxId()+1);
        developer.setAccountId(id_account);
        developer.setSkillIds(skillId);
        developer.setAccountStatusId(id_accountStatus);
        return developerRepository.create(developer);
    }

    public Developer update(String name, Long id, Long id_account, Set<Long> skillId, Long id_accountStatus) throws IOException, SQLException {
        Developer developer = new Developer();
        developer.setName(name);
        developer.setId(id);
        developer.setAccountId(id_account);
        developer.setSkillIds(skillId);
        developer.setAccountStatusId(id_accountStatus);
        return developerRepository.update(developer);
    }

    public Developer getById(Long id) throws IOException, SQLException {
        return developerRepository.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        developerRepository.deleteById(id);
    }

    public List<Developer> getAll() throws IOException, SQLException {
        return developerRepository.getAll();
    }

}
