package com.informatica.zapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTeststoCycleRequest {
    @JsonProperty("components")
    private String components = null;

    @JsonProperty("cycleId")
    private String cycleId = null;

    @JsonProperty("fromCycleId")
    private String fromCycleId = null;

    @JsonProperty("fromVersionId")
    private String fromVersionId = null;

    @JsonProperty("hasDefects")
    private Boolean hasDefects = null;

    @JsonProperty("labels")
    private String labels = null;

    @JsonProperty("method")
    private String method = null;

    @JsonProperty("priorities")
    private String priorities = null;

    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("statuses")
    private String statuses = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("folderId")
    private Integer folderId = null;

    @JsonProperty("searchId")
    private Integer searchId = null;

    @JsonProperty("issues")
    private List<String> issues = new ArrayList<String>();

    @JsonProperty("jql")
    private String jql = null;


    public AddTeststoCycleRequest searchId(Integer searchId) {
        this.searchId = searchId;
        return this;
    }

    /**
     * Get searchId
     * @return searchId
     **/
    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public AddTeststoCycleRequest jql(String jql) {
        this.jql = jql;
        return this;
    }

    /**
     * Get jql
     * @return jql
     **/
    public String getJql() {
        return jql;
    }

    public void setJql(String jql) {
        this.jql = jql;
    }

    public AddTeststoCycleRequest addIssue(String issueId) {
        this.issues.add(issueId);
        return this;
    }

    /**
     * Get issues
     * @return issues
     **/
    public java.util.List<String> getIssues() {
        return issues;
    }

    public void setIssues(java.util.List<String> issues) {
        this.issues = issues;
    }



    public AddTeststoCycleRequest components(String components) {
        this.components = components;
        return this;
    }

    /**
     * Get components
     * @return components
     **/
    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public AddTeststoCycleRequest cycleId(String cycleId) {
        this.cycleId = cycleId;
        return this;
    }

    /**
     * Get cycleId
     * @return cycleId
     **/
    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public AddTeststoCycleRequest fromCycleId(String fromCycleId) {
        this.fromCycleId = fromCycleId;
        return this;
    }

    /**
     * Get fromCycleId
     * @return fromCycleId
     **/
    public String getFromCycleId() {
        return fromCycleId;
    }

    public void setFromCycleId(String fromCycleId) {
        this.fromCycleId = fromCycleId;
    }

    public AddTeststoCycleRequest fromVersionId(String fromVersionId) {
        this.fromVersionId = fromVersionId;
        return this;
    }

    /**
     * Get fromVersionId
     * @return fromVersionId
     **/
    public String getFromVersionId() {
        return fromVersionId;
    }

    public void setFromVersionId(String fromVersionId) {
        this.fromVersionId = fromVersionId;
    }

    public AddTeststoCycleRequest hasDefects(Boolean hasDefects) {
        this.hasDefects = hasDefects;
        return this;
    }

    /**
     * Get hasDefects
     * @return hasDefects
     **/
    public Boolean isHasDefects() {
        return hasDefects;
    }

    public void setHasDefects(Boolean hasDefects) {
        this.hasDefects = hasDefects;
    }

    public AddTeststoCycleRequest labels(String labels) {
        this.labels = labels;
        return this;
    }

    /**
     * Get labels
     * @return labels
     **/
    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public AddTeststoCycleRequest method(String method) {
        this.method = method;
        return this;
    }

    /**
     * Get method
     * @return method
     **/
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public AddTeststoCycleRequest priorities(String priorities) {
        this.priorities = priorities;
        return this;
    }

    /**
     * Get priorities
     * @return priorities
     **/
    public String getPriorities() {
        return priorities;
    }

    public void setPriorities(String priorities) {
        this.priorities = priorities;
    }

    public AddTeststoCycleRequest projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     * @return projectId
     **/
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public AddTeststoCycleRequest statuses(String statuses) {
        this.statuses = statuses;
        return this;
    }

    /**
     * Get statuses
     * @return statuses
     **/
    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public AddTeststoCycleRequest versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * Get versionId
     * @return versionId
     **/
    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public AddTeststoCycleRequest folderId(Integer folderId) {
        this.folderId = folderId;
        return this;
    }

    /**
     * Get folderId
     * @return folderId
     **/
    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddTeststoCycleRequest AddTeststoCycleRequest = (AddTeststoCycleRequest) o;
        return Objects.equals(this.components, AddTeststoCycleRequest.components) &&
                Objects.equals(this.cycleId, AddTeststoCycleRequest.cycleId) &&
                Objects.equals(this.fromCycleId, AddTeststoCycleRequest.fromCycleId) &&
                Objects.equals(this.fromVersionId, AddTeststoCycleRequest.fromVersionId) &&
                Objects.equals(this.hasDefects, AddTeststoCycleRequest.hasDefects) &&
                Objects.equals(this.labels, AddTeststoCycleRequest.labels) &&
                Objects.equals(this.method, AddTeststoCycleRequest.method) &&
                Objects.equals(this.priorities, AddTeststoCycleRequest.priorities) &&
                Objects.equals(this.projectId, AddTeststoCycleRequest.projectId) &&
                Objects.equals(this.statuses, AddTeststoCycleRequest.statuses) &&
                Objects.equals(this.versionId, AddTeststoCycleRequest.versionId) &&
                Objects.equals(this.folderId, AddTeststoCycleRequest.folderId)&&
                Objects.equals(this.searchId, AddTeststoCycleRequest.searchId)&&
                Objects.equals(this.issues, AddTeststoCycleRequest.issues)&&
                Objects.equals(this.jql, AddTeststoCycleRequest.jql);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components, cycleId, fromCycleId, fromVersionId, hasDefects, labels, method, priorities, projectId, statuses, versionId, folderId, searchId, issues, jql);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddTeststoCycleRequest {\n");

        sb.append("    components: ").append(toIndentedString(components)).append("\n");
        sb.append("    cycleId: ").append(toIndentedString(cycleId)).append("\n");
        sb.append("    fromCycleId: ").append(toIndentedString(fromCycleId)).append("\n");
        sb.append("    fromVersionId: ").append(toIndentedString(fromVersionId)).append("\n");
        sb.append("    hasDefects: ").append(toIndentedString(hasDefects)).append("\n");
        sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
        sb.append("    method: ").append(toIndentedString(method)).append("\n");
        sb.append("    priorities: ").append(toIndentedString(priorities)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    statuses: ").append(toIndentedString(statuses)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    folderId: ").append(toIndentedString(folderId)).append("\n");
        sb.append("    searchId: ").append(toIndentedString(searchId)).append("\n");
        sb.append("    issues: ").append(toIndentedString(issues)).append("\n");
        sb.append("    jql: ").append(toIndentedString(jql)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
