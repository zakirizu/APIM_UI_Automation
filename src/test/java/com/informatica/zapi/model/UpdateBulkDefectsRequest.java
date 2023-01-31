package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBulkDefectsRequest {
    @JsonProperty("executions")
    private java.util.List<String> executions = new java.util.ArrayList<String>();

    @JsonProperty("defects")
    private java.util.List<String> defects = new java.util.ArrayList<String>();

    public UpdateBulkDefectsRequest executions(java.util.List<String> executions) {
        this.executions = executions;
        return this;
    }

    public UpdateBulkDefectsRequest addExecutionsItem(String executionsItem) {
        this.executions.add(executionsItem);
        return this;
    }

    /**
     * Get executions
     *
     * @return executions
     **/
    public java.util.List<String> getExecutions() {
        return executions;
    }

    public void setExecutions(java.util.List<String> executions) {
        this.executions = executions;
    }

    public UpdateBulkDefectsRequest defects(java.util.List<String> defects) {
        this.defects = defects;
        return this;
    }

    public UpdateBulkDefectsRequest addDefectsItem(String defectsItem) {
        this.defects.add(defectsItem);
        return this;
    }

    /**
     * Get defects
     *
     * @return defects
     **/
    public java.util.List<String> getDefects() {
        return defects;
    }

    public void setDefects(java.util.List<String> defects) {
        this.defects = defects;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateBulkDefectsRequest UpdateBulkDefectsRequest = (UpdateBulkDefectsRequest) o;
        return Objects.equals(this.executions, UpdateBulkDefectsRequest.executions) &&
                Objects.equals(this.defects, UpdateBulkDefectsRequest.defects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executions, defects);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateBulkDefectsRequest {\n");

        sb.append("    executions: ").append(toIndentedString(executions)).append("\n");
        sb.append("    defects: ").append(toIndentedString(defects)).append("\n");
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
