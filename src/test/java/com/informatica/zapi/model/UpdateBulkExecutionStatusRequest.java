package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBulkExecutionStatusRequest {
    @JsonProperty("executions")
    private java.util.List<String> executions = new java.util.ArrayList<String>();

    @JsonProperty("status")
    private String status = null;

    public UpdateBulkExecutionStatusRequest executions(java.util.List<String> executions) {
        this.executions = executions;
        return this;
    }

    public UpdateBulkExecutionStatusRequest addExecutionsItem(String executionsItem) {
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

    public UpdateBulkExecutionStatusRequest status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateBulkExecutionStatusRequest UpdateBulkExecutionStatusRequest = (UpdateBulkExecutionStatusRequest) o;
        return Objects.equals(this.executions, UpdateBulkExecutionStatusRequest.executions) &&
                Objects.equals(this.status, UpdateBulkExecutionStatusRequest.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executions, status);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateBulkExecutionStatusRequest {\n");

        sb.append("    executions: ").append(toIndentedString(executions)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
