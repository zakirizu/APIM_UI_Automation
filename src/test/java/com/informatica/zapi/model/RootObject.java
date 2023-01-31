package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootObject {
    public RootObject(Issue fields) {
        this.fields = fields;
    }

    @JsonProperty("fields")
    private Issue fields;

    public Issue getFields() {
        return this.fields;
    }

    public void setFields(Issue fields) {
        this.fields = fields;
    }
}
