package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cycle
 */
public class Cycle {
    @JsonProperty("totalExecutions")
    private Integer totalExecutions = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("totalExecuted")
    private Integer totalExecuted = null;

    @JsonProperty("started")
    private String started = null;

    @JsonProperty("versionName")
    private String versionName = null;

    @JsonProperty("sprintId")
    private Integer sprintId = null;

    @JsonProperty("expand")
    private String expand = null;

    @JsonProperty("projectKey")
    private String projectKey = null;

    @JsonProperty("versionId")
    private Integer versionId = null;

    @JsonProperty("environment")
    private String environment = null;

    @JsonProperty("build")
    private String build = null;

    @JsonProperty("createdBy")
    private String createdBy = null;

    @JsonProperty("ended")
    private String ended = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("modifiedBy")
    private String modifiedBy = null;

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("projectId")
    private Integer projectId = null;

    @JsonProperty("createdByDisplay")
    private String createdByDisplay = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("executionSummaries")
    private ExecutionSummaries executionSummaries = null;

    public Cycle totalExecutions(Integer totalExecutions) {
        this.totalExecutions = totalExecutions;
        return this;
    }

    /**
     * Get totalExecutions
     * @return totalExecutions
     **/
    public Integer getTotalExecutions() {
        return totalExecutions;
    }

    public void setTotalExecutions(Integer totalExecutions) {
        this.totalExecutions = totalExecutions;
    }

    public Cycle endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     * @return endDate
     **/
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Cycle description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cycle totalExecuted(Integer totalExecuted) {
        this.totalExecuted = totalExecuted;
        return this;
    }

    /**
     * Get totalExecuted
     * @return totalExecuted
     **/
    public Integer getTotalExecuted() {
        return totalExecuted;
    }

    public void setTotalExecuted(Integer totalExecuted) {
        this.totalExecuted = totalExecuted;
    }

    public Cycle started(String started) {
        this.started = started;
        return this;
    }

    /**
     * Get started
     * @return started
     **/
    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public Cycle versionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    /**
     * Get versionName
     * @return versionName
     **/
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Cycle sprintId(Integer sprintId) {
        this.sprintId = sprintId;
        return this;
    }

    /**
     * Get sprintId
     * @return sprintId
     **/
    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    public Cycle expand(String expand) {
        this.expand = expand;
        return this;
    }

    /**
     * Get expand
     * @return expand
     **/
    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public Cycle projectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    /**
     * Get projectKey
     * @return projectKey
     **/
    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public Cycle versionId(Integer versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * Get versionId
     * @return versionId
     **/
    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Cycle environment(String environment) {
        this.environment = environment;
        return this;
    }

    /**
     * Get environment
     * @return environment
     **/
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Cycle build(String build) {
        this.build = build;
        return this;
    }

    /**
     * Get build
     * @return build
     **/
    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Cycle createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Get createdBy
     * @return createdBy
     **/
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Cycle ended(String ended) {
        this.ended = ended;
        return this;
    }

    /**
     * Get ended
     * @return ended
     **/
    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public Cycle name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     * @return name
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cycle modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    /**
     * Get modifiedBy
     * @return modifiedBy
     **/
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Cycle id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cycle projectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     * @return projectId
     **/
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Cycle createdByDisplay(String createdByDisplay) {
        this.createdByDisplay = createdByDisplay;
        return this;
    }

    /**
     * Get createdByDisplay
     * @return createdByDisplay
     **/
    public String getCreatedByDisplay() {
        return createdByDisplay;
    }

    public void setCreatedByDisplay(String createdByDisplay) {
        this.createdByDisplay = createdByDisplay;
    }

    public Cycle startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     * @return startDate
     **/
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Cycle executionSummaries(ExecutionSummaries executionSummaries) {
        this.executionSummaries = executionSummaries;
        return this;
    }

    /**
     * Get executionSummaries
     * @return executionSummaries
     **/
    public ExecutionSummaries getExecutionSummaries() {
        return executionSummaries;
    }

    public void setExecutionSummaries(ExecutionSummaries executionSummaries) {
        this.executionSummaries = executionSummaries;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cycle cycle = (Cycle) o;
        return Objects.equals(this.totalExecutions, cycle.totalExecutions) &&
                Objects.equals(this.endDate, cycle.endDate) &&
                Objects.equals(this.description, cycle.description) &&
                Objects.equals(this.totalExecuted, cycle.totalExecuted) &&
                Objects.equals(this.started, cycle.started) &&
                Objects.equals(this.versionName, cycle.versionName) &&
                Objects.equals(this.sprintId, cycle.sprintId) &&
                Objects.equals(this.expand, cycle.expand) &&
                Objects.equals(this.projectKey, cycle.projectKey) &&
                Objects.equals(this.versionId, cycle.versionId) &&
                Objects.equals(this.environment, cycle.environment) &&
                Objects.equals(this.build, cycle.build) &&
                Objects.equals(this.createdBy, cycle.createdBy) &&
                Objects.equals(this.ended, cycle.ended) &&
                Objects.equals(this.name, cycle.name) &&
                Objects.equals(this.modifiedBy, cycle.modifiedBy) &&
                Objects.equals(this.id, cycle.id) &&
                Objects.equals(this.projectId, cycle.projectId) &&
                Objects.equals(this.createdByDisplay, cycle.createdByDisplay) &&
                Objects.equals(this.startDate, cycle.startDate) &&
                Objects.equals(this.executionSummaries, cycle.executionSummaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalExecutions, endDate, description, totalExecuted, started, versionName, sprintId, expand, projectKey, versionId, environment, build, createdBy, ended, name, modifiedBy, id, projectId, createdByDisplay, startDate, executionSummaries);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Cycle {\n");

        sb.append("    totalExecutions: ").append(toIndentedString(totalExecutions)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    totalExecuted: ").append(toIndentedString(totalExecuted)).append("\n");
        sb.append("    started: ").append(toIndentedString(started)).append("\n");
        sb.append("    versionName: ").append(toIndentedString(versionName)).append("\n");
        sb.append("    sprintId: ").append(toIndentedString(sprintId)).append("\n");
        sb.append("    expand: ").append(toIndentedString(expand)).append("\n");
        sb.append("    projectKey: ").append(toIndentedString(projectKey)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
        sb.append("    build: ").append(toIndentedString(build)).append("\n");
        sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
        sb.append("    ended: ").append(toIndentedString(ended)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    createdByDisplay: ").append(toIndentedString(createdByDisplay)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    executionSummaries: ").append(toIndentedString(executionSummaries)).append("\n");
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