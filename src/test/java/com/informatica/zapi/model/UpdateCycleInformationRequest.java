package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UpdateCycleInformationRequest
 */
public class UpdateCycleInformationRequest {
    @JsonProperty("id")
    private String id = null;

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

    @JsonProperty("versionId")
    private String versionId = null;

    @JsonProperty("folderId")
    private Integer folderId = null;

    public UpdateCycleInformationRequest id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UpdateCycleInformationRequest name(String name) {
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

    public UpdateCycleInformationRequest build(String build) {
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

    public UpdateCycleInformationRequest environment(String environment) {
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

    public UpdateCycleInformationRequest description(String description) {
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

    public UpdateCycleInformationRequest startDate(String startDate) {
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

    public UpdateCycleInformationRequest endDate(String endDate) {
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

    public UpdateCycleInformationRequest versionId(String versionId) {
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

    public UpdateCycleInformationRequest folderId(Integer folderId) {
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
        UpdateCycleInformationRequest UpdateCycleInformationRequest = (UpdateCycleInformationRequest) o;
        return Objects.equals(this.id, UpdateCycleInformationRequest.id) &&
                Objects.equals(this.name, UpdateCycleInformationRequest.name) &&
                Objects.equals(this.build, UpdateCycleInformationRequest.build) &&
                Objects.equals(this.environment, UpdateCycleInformationRequest.environment) &&
                Objects.equals(this.description, UpdateCycleInformationRequest.description) &&
                Objects.equals(this.startDate, UpdateCycleInformationRequest.startDate) &&
                Objects.equals(this.endDate, UpdateCycleInformationRequest.endDate) &&
                Objects.equals(this.versionId, UpdateCycleInformationRequest.versionId) &&
                Objects.equals(this.folderId, UpdateCycleInformationRequest.folderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, build, environment, description, startDate, endDate, versionId, folderId);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateCycleInformationRequest {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    build: ").append(toIndentedString(build)).append("\n");
        sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    folderId: ").append(toIndentedString(folderId)).append("\n");
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
