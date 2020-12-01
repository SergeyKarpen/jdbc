package com.karpen.jdbc.service.serviceImpl;

import com.karpen.jdbc.model.Developer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeveloperServiceImplTest {
    private final DeveloperServiceImpl mockDeveloperService = mock(DeveloperServiceImpl.class);
    private final Developer developer = mock(Developer.class);

    // @Test create
    @Test
    void create_return_UpDeveloper() throws SQLException {
        Developer creatDeveloper = mockDeveloperService.create(developer);
        assertEquals(creatDeveloper, mockDeveloperService.create(developer));
    }
    @Test
    void create_check() throws SQLException {
        mockDeveloperService.create(developer);
        verify(mockDeveloperService).create(developer);
    }

    // @Test update
    @Test
    void update_check() throws SQLException {
        mockDeveloperService.update(developer);
        verify(mockDeveloperService).update(developer);
    }

    @Test
    void update_return_developer() throws SQLException {
        Developer developer1 = mockDeveloperService.update(developer);
        assertEquals(developer1, mockDeveloperService.update(developer));
    }

    // @Test getAll
    @Test
    void getAll_check() throws SQLException {
        mockDeveloperService.getAll();
        verify(mockDeveloperService).getAll();
    }

    @Test
    void getAll_return_developerList() throws SQLException {
        List<Developer> developerList = new ArrayList<>();
        assertEquals(developerList, mockDeveloperService.getAll());
    }

    // @Test getById
    @Test
    void getById_return_check() throws SQLException {
        mockDeveloperService.getById(anyLong());
        verify(mockDeveloperService).getById(anyLong());
    }

    @Test
    void getById_return_developer() throws SQLException {
        when(mockDeveloperService.getById(1L)).thenReturn(developer);
        assertEquals(developer, mockDeveloperService.getById(1L));
    }

    @Test
    void getById_return_throw() throws SQLException {
        when(mockDeveloperService.getById(-1L)).thenThrow(NullPointerException.class);
    }

    // @Test deleteById
    @Test
    void deleteById_check() throws SQLException {
        mockDeveloperService.deleteById(anyLong());
        verify(mockDeveloperService).deleteById(anyLong());
    }
    @Test
    void deleteById_return_throw() throws SQLException {
        doThrow(new NullPointerException()).when(mockDeveloperService).deleteById(-1L);
    }

    // @Test maxId
    @Test
    void maxId() throws SQLException {
        mockDeveloperService.maxId();
        verify(mockDeveloperService).maxId();
    }
}