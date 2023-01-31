package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ExecutionSummaries2
 */
public class ExecutionSummaries {
    @JsonProperty("executionSummary")
    private java.util.List<ExecutionSummary> executionSummary = new java.util.ArrayList<ExecutionSummary>();

    public ExecutionSummaries executionSummary(java.util.List<ExecutionSummary> executionSummary) {
        this.executionSummary = executionSummary;
        return this;
    }

    public ExecutionSummaries addExecutionSummaryItem(ExecutionSummary executionSummaryItem) {
        this.executionSummary.add(executionSummaryItem);
        return this;
    }

    /**
     * Get executionSummary
     * @return executionSummary
     **/
    public java.util.List<ExecutionSummary> getExecutionSummary() {
        return executionSummary;
    }

    public void setExecutionSummary(java.util.List<ExecutionSummary> executionSummary) {
        this.executionSummary = executionSummary;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExecutionSummaries executionSummaries2 = (ExecutionSummaries) o;
        return Objects.equals(this.executionSummary, executionSummaries2.executionSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executionSummary);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExecutionSummaries {\n");

        sb.append("    executionSummary: ").append(toIndentedString(executionSummary)).append("\n");
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