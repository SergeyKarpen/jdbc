package com.karpen.jdbc.service.serviceImpl;

import com.karpen.jdbc.model.Skill;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SkillsServiceImplTest {

    private final SkillsServiceImpl mockSkillsService = mock(SkillsServiceImpl.class);
    private final Skill skill = mock(Skill.class);

    // @Test create
    @Test
    void create_returnUpSkill() throws SQLException {
        Skill createSkill = mockSkillsService.create(skill);
        assertEquals(createSkill, mockSkillsService.create(skill));
    }
    @Test
    void create_check() throws SQLException {
        mockSkillsService.create(skill);
        verify(mockSkillsService).create(skill);
    }

    // @Test update
    @Test
    void update_check() throws SQLException {
        mockSkillsService.update(skill);
        verify(mockSkillsService).update(skill);
    }

    @Test
    void update_return_skill() throws SQLException {
        Skill skill1 = mockSkillsService.update(skill);
        assertEquals(skill1, mockSkillsService.update(skill));
    }

    // @Test getAll
    @Test
    void getAll_check() throws SQLException {
        mockSkillsService.getAll();
        verify(mockSkillsService).getAll();
    }

    @Test
    void getAll_return_skillsList() throws SQLException {
        List<Skill> skillList = new ArrayList<>();
        assertEquals(skillList, mockSkillsService.getAll());
    }

    // @Test getById
    @Test
    void getById_return_check() throws SQLException {
        mockSkillsService.getById(anyLong());
        verify(mockSkillsService).getById(anyLong());
    }

    @Test
    void getById_return_skill() throws SQLException {
        when(mockSkillsService.getById(1L)).thenReturn(skill);
        assertEquals(skill, mockSkillsService.getById(1L));
    }

    @Test
    void getById_return_throw() throws SQLException {
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