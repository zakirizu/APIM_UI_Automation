package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetCycleInformationResponse
 */
public class GetCycleInformationResponse {
    @JsonProperty("endDate")
    private String endDate = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("versionName")
    private String versionName = null;

    @JsonProperty("sprintId")
    private String sprintId = null;

    @JsonProperty("versionId")
    private Integer versionId = null;

    @JsonProperty("environment")
    private String environment = null;

    @JsonProperty("build")
    private String build = null;

    @JsonProperty("createdBy")
    private String createdBy = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("modifiedBy")
    private String modifiedBy = null;

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("projectId")
    private Integer projectId = null;

    @JsonProperty("startDate")
    private String startDate = null;

    public GetCycleInformationResponse endDate(String endDate) {
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

    public GetCycleInformationResponse description(String description) {
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

    public GetCycleInformationResponse versionName(String versionName) {
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

    public GetCycleInformationResponse sprintId(String sprintId) {
        this.sprintId = sprintId;
        return this;
    }

    /**
     * Get sprintId
     * @return sprintId
     **/
    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public GetCycleInformationResponse versionId(Integer versionId) {
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

    public GetCycleInformationResponse environment(String environment) {
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

    public GetCycleInformationResponse build(String build) {
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

    public GetCycleInformationResponse createdBy(String createdBy) {
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

    public GetCycleInformationResponse name(String name) {
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

    public GetCycleInformationResponse modifiedBy(String modifiedBy) {
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

    public GetCycleInformationResponse id(Integer id) {
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

    public GetCycleInformationResponse projectId(Integer projectId) {
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

    public GetCycleInformationResponse startDate(String startDate) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetCycleInformationResponse GetCycleInformationResponse = (GetCycleInformationResponse) o;
        return Objects.equals(this.endDate, GetCycleInformationResponse.endDate) &&
                Objects.equals(this.description, GetCycleInformationResponse.description) &&
                Objects.equals(this.versionName, GetCycleInformationResponse.versionName) &&
                Objects.equals(this.sprintId, GetCycleInformationResponse.sprintId) &&
                Objects.equals(this.versionId, GetCycleInformationResponse.versionId) &&
                Objects.equals(this.environment, GetCycleInformationResponse.environment) &&
                Objects.equals(this.build, GetCycleInformationResponse.build) &&
                Objects.equals(this.createdBy, GetCycleInformationResponse.createdBy) &&
                Objects.equals(this.name, GetCycleInformationResponse.name) &&
                Objects.equals(this.modifiedBy, GetCycleInformationResponse.modifiedBy) &&
                Objects.equals(this.id, GetCycleInformationResponse.id) &&
                Objects.equals(this.projectId, GetCycleInformationResponse.projectId) &&
                Objects.equals(this.startDate, GetCycleInformationResponse.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, description, versionName, sprintId, versionId, environment, build, createdBy, name, modifiedBy, id, projectId, startDate);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetCycleInformationResponse {\n");

        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    versionName: ").append(toIndentedString(versionName)).append("\n");
        sb.append("    sprintId: ").append(toIndentedString(sprintId)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
        sb.append("    build: ").append(toIndentedString(build)).append("\n");
        sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
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
