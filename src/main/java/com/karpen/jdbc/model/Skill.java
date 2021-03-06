package com.karpen.jdbc.model;

public class Skill extends BaseEntity {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " name=" + name;
    }
}
