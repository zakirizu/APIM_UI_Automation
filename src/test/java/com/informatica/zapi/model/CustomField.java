package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomField {
    @JsonProperty("id")
    String id;
    @JsonProperty("value")
    String value;

    public CustomField(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public CustomField(String value) {
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
}
