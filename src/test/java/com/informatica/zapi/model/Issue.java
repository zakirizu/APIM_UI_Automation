package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Issue {
    @JsonProperty("id")
    String id;
    @JsonProperty("project")
    Project project;
    @JsonProperty("summary")
    String summary;
    @JsonProperty("issuetype")
    IssueType issuetype;
    @JsonProperty("assignee")
    User assignee;
    @JsonProperty("reporter")
    User reporter;
    @JsonProperty("key")
    String key;
    @JsonProperty("description")
    String description;
    @JsonProperty("environment")
    String environment;
    @JsonProperty("labels")
    String[] labels;
    @JsonProperty("priority")
    Priority priority;
    @JsonProperty("fixVersions")
    List<Version> fixVersions;
    @JsonProperty("components")
    List<Component> components;
    @JsonProperty("custom")
    Map<String, String> custom;

    @JsonProperty("customfield_22382")
    AutomationStatus automationStatuses;

    @JsonProperty("customfield_22380")
    TestType testTypes;

    public CascadeCustomField getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(CascadeCustomField subComponent) {
        this.subComponent = subComponent;
    }

    @JsonProperty("customfield_13580")
    CascadeCustomField subComponent;

    @JsonProperty("customfield_21386")
    String testStep;

    @JsonProperty("customfield_22381")
    String preCondition;

    @JsonProperty("customfield_10783")
    String expectedResult;

    public String getTestStep() {
        return testStep;
    }

    @JsonAnySetter
    public void setTestStep(String testStep) {
        this.testStep = testStep;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<Version> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(List<Version> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Map<String, String> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, String> custom) {
        this.custom = custom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AutomationStatus getAutomationStatuses() {
        return automationStatuses;
    }

    public void setAutomationStatuses(AutomationStatus automationStatuses) {
        this.automationStatuses = automationStatuses;
    }

    public TestType getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(TestType testTypes) {
        this.testTypes = testTypes;
    }

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

}
