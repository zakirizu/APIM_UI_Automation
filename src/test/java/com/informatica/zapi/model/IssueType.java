package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class IssueType {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("decription")
    String description;
    @JsonProperty("fields")
    Map<String, Object> fields;

    public IssueType(String id) {
        this.id = id;
    }

    public IssueType(String id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public static final IssueType Test = new IssueType("13701", "Test");
}
