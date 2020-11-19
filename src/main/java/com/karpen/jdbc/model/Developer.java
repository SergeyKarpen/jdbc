package com.karpen.jdbc.model;

import java.util.Set;

public class Developer extends BasicKitModel {

    private String accountStatus;
    private Long accountId;
    private Set<Long> skillIds;
    private Account account;
    private Set<Skill> skills;

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Set<Long> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(Set<Long> skillIds) {
        this.skillIds = skillIds;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }


    @Override
    public String toString() {
        return id + " " + name + " " + account + " " + skills + "\n";
    }
}