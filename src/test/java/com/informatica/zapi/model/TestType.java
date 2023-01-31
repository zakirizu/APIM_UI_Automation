package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestType {

    @JsonProperty("id")
    String id;
    @JsonProperty("value")
    String value;

    public TestType(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final TestType Regression = new TestType("64191", "Regression");
    public static final TestType Sanity = new TestType("64190", "Sanity");

}
