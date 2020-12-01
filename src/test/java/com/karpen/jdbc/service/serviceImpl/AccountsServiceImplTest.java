package com.karpen.jdbc.service.serviceImpl;

import com.karpen.jdbc.model.Account;
import com.karpen.jdbc.model.Skill;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AccountsServiceImplTest {

    private final AccountsServiceImpl mockAccountService = mock(AccountsServiceImpl.class);
    private final Account account = mock(Account.class);

    // @Test create
    @Test
    void create_returnUpAccount() throws SQLException {
        Account createAccount = mockAccountService.create(account);
        assertEquals(createAccount, mockAccountService.create(account));
    }
    @Test
    void create_check() throws SQLException {
        mockAccountService.create(account);
        verify(mockAccountService).create(account);
    }

    // @Test update
    @Test
    void update_check() throws SQLException {
        mockAccountService.update(account);
        verify(mockAccountService).update(account);
    }

    @Test
    void update_return_skill() throws SQLException {
        Account account1 = mockAccountService.update(account);
        assertEquals(account1, mockAccountService.update(account));
    }

    // @Test getAll
    @Test
    void getAll_check() throws SQLException {
        mockAccountService.getAll();
        verify(mockAccountService).getAll();
    }

    @Test
    void getAll_return_skillsList() throws SQLException {
        List<Skill> skillList = new ArrayList<>();
        assertEquals(skillList, mockAccountService.getAll());
    }

    // @Test getById
    @Test
    void getById_return_check() throws SQLException {
        mockAccountService.getById(anyLong());
        verify(mockAccountService).getById(anyLong());
    }

    @Test
    void getById_return_skill() throws SQLException {
        when(mockAccountService.getById(1L)).thenReturn(account);
        assertEquals(account, mockAccountService.getById(1L));
    }

    @Test
    void getById_return_throw() throws SQLException {
        when(mockAccountService.getById(-1L)).thenThrow(NullPointerException.class);
    }

    // @Test deleteById
    @Test
    void deleteById_check() throws SQLException {
        mockAccountService.deleteById(anyLong());
        verify(mockAccountService).deleteById(anyLong());
    }
    @Test
    void deleteById_return_throw() throws SQLException {
        doThrow(new NullPointerException()).when(mockAccountService).deleteById(-1L);
    }
    // @Test maxId
    @Test
    void maxId() throws SQLException {
        mockAccountService.maxId();
        verify(mockAccountService).maxId();
    }
}