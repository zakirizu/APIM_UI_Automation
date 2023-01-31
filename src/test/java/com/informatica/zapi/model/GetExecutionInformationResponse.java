package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetExecutionInformationResponse
 */
public class GetExecutionInformationResponse {
    @JsonProperty("execution")
    private Execution execution = null;

    public GetExecutionInformationResponse execution(Execution execution) {
        this.execution = execution;
        return this;
    }

    /**
     * Get execution
     *
     * @return execution
     **/
    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetExecutionInformationResponse GetExecutionInformationResponse = (GetExecutionInformationResponse) o;
        return Objects.equals(this.execution, GetExecutionInformationResponse.execution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(execution);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetExecutionInformationResponse {\n");

        sb.append("    execution: ").append(toIndentedString(execution)).append("\n");
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
