package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Priority {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;

    public Priority(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Priority P0 = new Priority("1", "P0/Blocker");
    public static final Priority P1 = new Priority("2", "P1/Major");
    public static final Priority P2 = new Priority("3", "P2/Normal");
    public static final Priority P3 = new Priority("4", "P3/Minor");
    public static final Priority P4 = new Priority("5", "P4");

}
