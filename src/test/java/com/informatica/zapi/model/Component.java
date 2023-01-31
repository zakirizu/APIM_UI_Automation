package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Component {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    public Component(String name) {
        this.name = name;
    }

    public Component(String id, String name) {
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

    public static final Priority ILMDynamicDataMasking = new Priority("16914", "ILM Dynamic Data Masking");
    public static final Priority DDM = new Priority("26812", "DDM");
    public static final Priority DDMforSAP = new Priority("17127", "DDM for SAP");

    public static final Priority HTTPMaskingContentHandlers = new Priority("17296", "HTTP Masking - Content Handlers");
    public static final Priority HTTPMaskingGenericRules = new Priority("17297", "HTTP Masking - Generic Rules");
    public static final Priority HTTPMaskingIntegrations = new Priority("17301", "HTTP Masking - Integrations");
    public static final Priority HTTPMaskingNewActions = new Priority("17300", "HTTP Masking - New Actions");
    public static final Priority HTTPMaskingNewMatchers = new Priority("17299", "HTTP Masking - New Matchers");
    public static final Priority HTTPMaskingTransformers = new Priority("17298", "HTTP Masking - Transformers");
}
