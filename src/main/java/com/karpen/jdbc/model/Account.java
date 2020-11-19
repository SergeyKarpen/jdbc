package com.karpen.jdbc.model;


public class Account extends BasicKitModel {
    private AccountStatus accountStatus;

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}

