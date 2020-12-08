package com.karpen.jdbc.model;


public class Account extends BaseEntity {

    private String content;
    private AccountStatus accountStatus;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "content=" + content +
                ", accountStatus=" + accountStatus +
                ", id=" + id;
    }
}

