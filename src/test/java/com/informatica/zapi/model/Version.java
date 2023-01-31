package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Version {

    @JsonProperty("name")
    String name;

    public Version(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}