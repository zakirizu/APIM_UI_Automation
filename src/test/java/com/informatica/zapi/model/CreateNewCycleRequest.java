package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateNewCycleRequest
 */
public class CreateNewCycleRequest {
    @JsonProperty("clonedCycleId")
    private String clonedCycleId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("build")
    private String build = null;

    @JsonProperty("environment")
    private String environment = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("startDate")
    private String startDate = null;

    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("sprintId")
    private Integer sprintId = null;

    public CreateNewCycleRequest clonedCycleId(String clonedCycleId) {
        this.clonedCycleId = clonedCycleId;
        return this;
    }

    /**
     * Get clonedCycleId
     *
     * @return clonedCycleId
     **/
    public String getClonedCycleId() {
        return clonedCycleId;
    }

    public void setClonedCycleId(String clonedCycleId) {
        this.clonedCycleId = clonedCycleId;
    }

    public CreateNewCycleRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateNewCycleRequest build(String build) {
        this.build = build;
        return this;
    }

    /**
     * Get build
     *
     * @return build
     **/
    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public CreateNewCycleRequest environment(String environment) {
        this.environment = environment;
        return this;
    }

    /**
     * Get environment
     *
     * @return environment
     **/
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public CreateNewCycleRequest description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateNewCycleRequest startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     *
     * @return startDate
     **/
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public CreateNewCycleRequest endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     *
     * @return endDate
     **/
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CreateNewCycleRequest projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     *
     * @return projectId
     **/
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public CreateNewCycleRequest versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * Get versionId
     *
     * @return versionId
     **/
    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public CreateNewCycleRequest sprintId(Integer sprintId) {
        this.sprintId = sprintId;
        return this;
    }

    /**
     * Get sprintId
     *
     * @return sprintId
     **/
    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateNewCycleRequest CreateNewCycleRequest = (CreateNewCycleRequest) o;
        return Objects.equals(this.clonedCycleId, CreateNewCycleRequest.clonedCycleId)
                && Objects.equals(this.name, CreateNewCycleRequest.name)
                && Objects.equals(this.build, CreateNewCycleRequest.build)
                && Objects.equals(this.environment, CreateNewCycleRequest.environment)
                && Objects.equals(this.description, CreateNewCycleRequest.description)
                && Objects.equals(this.startDate, CreateNewCycleRequest.startDate)
                && Objects.equals(this.endDate, CreateNewCycleRequest.endDate)
                && Objects.equals(this.projectId, CreateNewCycleRequest.projectId)
                && Objects.equals(this.versionId, CreateNewCycleRequest.versionId)
                && Objects.equals(this.sprintId, CreateNewCycleRequest.sprintId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clonedCycleId, name, build, environment, description, startDate, endDate, projectId,
                versionId, sprintId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateNewCycleRequest {\n");

        sb.append("    clonedCycleId: ").append(toIndentedString(clonedCycleId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    build: ").append(toIndentedString(build)).append("\n");
        sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    sprintId: ").append(toIndentedString(sprintId)).append("\n");
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
