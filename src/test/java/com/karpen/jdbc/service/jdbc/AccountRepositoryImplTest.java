package com.karpen.jdbc.service.jdbc;

import com.karpen.jdbc.model.Account;
import com.karpen.jdbc.model.Skill;
import com.karpen.jdbc.repository.jdbc.JdbcAccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AccountRepositoryImplTest {

    private final JdbcAccountRepositoryImpl mockAccountService = mock(JdbcAccountRepositoryImpl.class);
    private final Account account = mock(Account.class);


    @Test
    void create_returnUpAccount() {
        Account createAccount = mockAccountService.create(account);
        assertEquals(createAccount, mockAccountService.create(account));
    }

    @Test
    void create_check() {
        mockAccountService.create(account);
        verify(mockAccountService).create(account);
    }


    @Test
    void update_check() {
        mockAccountService.update(account);
        verify(mockAccountService).update(account);
    }

    @Test
    void update_return_skill() {
        Account account1 = mockAccountService.update(account);
        assertEquals(account1, mockAccountService.update(account));
    }


    @Test
    void getAll_check() {
        mockAccountService.getAll();
        verify(mockAccountService).getAll();
    }

    @Test
    void getAll_return_skillsList()  {
        List<Skill> skillList = new ArrayList<>();
        assertEquals(skillList, mockAccountService.getAll());
    }


    @Test
    void getById_return_check() {
        mockAccountService.getById(anyLong());
        verify(mockAccountService).getById(anyLong());
    }

    @Test
    void getById_return_skill() {
        when(mockAccountService.getById(1L)).thenReturn(account);
        assertEquals(account, mockAccountService.getById(1L));
    }

    @Test
    void getById_return_throw() {
        when(mockAccountService.getById(-1L)).thenThrow(NullPointerException.class);
    }

    @Test
    void deleteById_check() {
        mockAccountService.deleteById(anyLong());
        verify(mockAccountService).deleteById(anyLong());
    }

    @Test
    void deleteById_return_throw() {
        doThrow(new NullPointerException()).when(mockAccountService).deleteById(-1L);
    }

    @Test
    void maxId() {
        mockAccountService.lastId();
        verify(mockAccountService).lastId();
    }
}