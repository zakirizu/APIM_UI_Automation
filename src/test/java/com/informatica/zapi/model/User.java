package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("name")
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
