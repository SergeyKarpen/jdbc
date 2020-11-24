package com.karpen.jdbc.model;

import java.util.Set;

public class Developer extends BasicKitModel {

    private Long accountStatusId;
    private Long accountId;
    private Set<Long> skillIds;

    public Long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Long accountStatusId) {
        this.accountStatusId = accountStatusId;
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

    @Override
    public String toString() {
        return id + " " + name + " " + " " + "\n";
    }
}