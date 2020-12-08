package com.karpen.jdbc.service.jdbc;

import com.karpen.jdbc.model.Developer;
import com.karpen.jdbc.repository.jdbc.JdbcDeveloperRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeveloperRepositoryImplTest {
    private final JdbcDeveloperRepositoryImpl mockDeveloperService = mock(JdbcDeveloperRepositoryImpl.class);
    private final Developer developer = mock(Developer.class);

    @Test
    void create_return_UpDeveloper() {
        Developer creatDeveloper = mockDeveloperService.create(developer);
        assertEquals(creatDeveloper, mockDeveloperService.create(developer));
    }

    @Test
    void create_check() {
        mockDeveloperService.create(developer);
        verify(mockDeveloperService).create(developer);
    }

    @Test
    void update_check() {
        mockDeveloperService.update(developer);
        verify(mockDeveloperService).update(developer);
    }

    @Test
    void update_return_developer() {
        Developer developer1 = mockDeveloperService.update(developer);
        assertEquals(developer1, mockDeveloperService.update(developer));
    }


    @Test
    void getAll_check() {
        mockDeveloperService.getAll();
        verify(mockDeveloperService).getAll();
    }

    @Test
    void getAll_return_developerList() {
        List<Developer> developerList = new ArrayList<>();
        assertEquals(developerList, mockDeveloperService.getAll());
    }


    @Test
    void getById_return_check() {
        mockDeveloperService.getById(anyLong());
        verify(mockDeveloperService).getById(anyLong());
    }

    @Test
    void getById_return_developer() {
        when(mockDeveloperService.getById(1L)).thenReturn(developer);
        assertEquals(developer, mockDeveloperService.getById(1L));
    }

    @Test
    void getById_return_throw() {
        when(mockDeveloperService.getById(-1L)).thenThrow(NullPointerException.class);
    }


    @Test
    void deleteById_check() {
        mockDeveloperService.deleteById(anyLong());
        verify(mockDeveloperService).deleteById(anyLong());
    }

    @Test
    void deleteById_return_throw() {
        doThrow(new NullPointerException()).when(mockDeveloperService).deleteById(-1L);
    }


    @Test
    void maxId() {
        mockDeveloperService.lastId();
        verify(mockDeveloperService).lastId();
    }
}