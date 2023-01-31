package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ExecutionSummary
 */
public class ExecutionSummary {
    @JsonProperty("count")
    private Integer count = null;

    @JsonProperty("statusKey")
    private Integer statusKey = null;

    @JsonProperty("statusName")
    private String statusName = null;

    @JsonProperty("statusColor")
    private String statusColor = null;

    @JsonProperty("statusDescription")
    private String statusDescription = null;

    public ExecutionSummary count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Get count
     * @return count
     **/
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ExecutionSummary statusKey(Integer statusKey) {
        this.statusKey = statusKey;
        return this;
    }

    /**
     * Get statusKey
     * @return statusKey
     **/
    public Integer getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(Integer statusKey) {
        this.statusKey = statusKey;
    }

    public ExecutionSummary statusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    /**
     * Get statusName
     * @return statusName
     **/
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public ExecutionSummary statusColor(String statusColor) {
        this.statusColor = statusColor;
        return this;
    }

    /**
     * Get statusColor
     * @return statusColor
     **/
    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public ExecutionSummary statusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        return this;
    }

    /**
     * Get statusDescription
     * @return statusDescription
     **/
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExecutionSummary executionSummary = (ExecutionSummary) o;
        return Objects.equals(this.count, executionSummary.count) &&
                Objects.equals(this.statusKey, executionSummary.statusKey) &&
                Objects.equals(this.statusName, executionSummary.statusName) &&
                Objects.equals(this.statusColor, executionSummary.statusColor) &&
                Objects.equals(this.statusDescription, executionSummary.statusDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, statusKey, statusName, statusColor, statusDescription);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExecutionSummary {\n");

        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    statusKey: ").append(toIndentedString(statusKey)).append("\n");
        sb.append("    statusName: ").append(toIndentedString(statusName)).append("\n");
        sb.append("    statusColor: ").append(toIndentedString(statusColor)).append("\n");
        sb.append("    statusDescription: ").append(toIndentedString(statusDescription)).append("\n");
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