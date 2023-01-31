package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CascadeCustomField {
    @JsonProperty("id")
    String id;
    @JsonProperty("value")
    String value;
    @JsonProperty("child")
    CustomField child;

    public CascadeCustomField(String id, String value, CustomField child) {
        this.id = id;
        this.value = value;
        this.child = child;
    }
    public CascadeCustomField(String value, CustomField child) {
        this.value = value;
        this.child = child;
    }

    public CascadeCustomField(String value) {
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

    public CustomField getChild() {
        return child;
    }

    public void setChild(CustomField child) {
        this.child = child;
    }
}