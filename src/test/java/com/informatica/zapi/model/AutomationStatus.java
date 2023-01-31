package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomationStatus {

    @JsonProperty("id")
    String id;
    @JsonProperty("value")
    String value;

    public AutomationStatus(String id, String value) {
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

    public static final AutomationStatus Planned = new AutomationStatus("64192", "Planned");
    public static final AutomationStatus Automated = new AutomationStatus("64193", "Automated");
    public static final AutomationStatus Not_Automatable = new AutomationStatus("64194", "Not Automatable");

}