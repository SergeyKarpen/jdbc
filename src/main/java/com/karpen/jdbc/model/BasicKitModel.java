package com.karpen.jdbc.model;

abstract class BasicKitModel {
    String name;
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " " + name ;
    }
}

