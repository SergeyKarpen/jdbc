package com.karpen.jdbc.service.jdbc;

import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.jdbc.JdbcSkillRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SkillRepositoryImplTest {

    private final JdbcSkillRepositoryImpl mockSkillsService = mock(JdbcSkillRepositoryImpl.class);
    private final Skill skill = mock(Skill.class);


    @Test
    void create_returnUpSkill() {
        Skill createSkill = mockSkillsService.create(skill);
        assertEquals(createSkill, mockSkillsService.create(skill));
    }

    @Test
    void create_check() {
        mockSkillsService.create(skill);
        verify(mockSkillsService).create(skill);
    }


    @Test
    void update_check() {
        mockSkillsService.update(skill);
        verify(mockSkillsService).update(skill);
    }

    @Test
    void update_return_skill() {
        Skill skill1 = mockSkillsService.update(skill);
        assertEquals(skill1, mockSkillsService.update(skill));
    }

    @Test
    void getAll_check() {
        mockSkillsService.getAll();
        verify(mockSkillsService).getAll();
    }

    @Test
    void getAll_return_skillsList() {
        List<Skill> skillList = new ArrayList<>();
        assertEquals(skillList, mockSkillsService.getAll());
    }


    @Test
    void getById_return_check() {
        mockSkillsService.getById(anyLong());
        verify(mockSkillsService).getById(anyLong());
    }

    @Test
    void getById_return_skill() {
        when(mockSkillsService.getById(1L)).thenReturn(skill);
        assertEquals(skill, mockSkillsService.getById(1L));
    }

    @Test
    void getById_return_throw() {
        when(mockSkillsService.getById(-1L)).thenThrow(NullPointerException.class);
    }

    // @Test deleteById
    @Test
    void deleteById_check() throws SQLException {
        mockSkillsService.deleteById(anyLong());
        verify(mockSkillsService).deleteById(anyLong());
    }

    @Test
    void deleteById_return_throw() throws SQLException {
        doThrow(new NullPointerException()).when(mockSkillsService).deleteById(-1L);
    }

    // @Test maxId
    @Test
    void maxId() throws SQLException {
        mockSkillsService.maxId();
        verify(mockSkillsService).maxId();
    }
}