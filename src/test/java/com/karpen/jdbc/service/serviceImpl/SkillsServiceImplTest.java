package com.karpen.jdbc.service.serviceImpl;

import com.karpen.jdbc.model.Skill;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SkillsServiceImplTest {
    SkillsServiceImpl mockSkillsService = mock(SkillsServiceImpl.class);

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {

    }

    @Test
    void getById() throws SQLException {
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("Test");

        mockSkillsService.getById(anyLong());
        verify(mockSkillsService).getById(anyLong());

        when(mockSkillsService.getById(1L)).thenReturn(skill);
        assertEquals(skill, mockSkillsService.getById(1L));

        when(mockSkillsService.getById(9999L)).thenThrow(NullPointerException.class);

        assertEquals(skill, mockSkillsService.getById(1L));
    }

    @Test
    void deleteById() {
    }

    @Test
    void maxId() {
    }
}