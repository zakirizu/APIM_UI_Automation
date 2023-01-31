package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobTokenResponse {
    @JsonProperty("jobProgressToken")
    private String jobProgressToken = null;

    public JobTokenResponse jobProgressToken(String jobProgressToken) {
        this.jobProgressToken = jobProgressToken;
        return this;
    }

    /**
     * Get jobProgressToken
     *
     * @return jobProgressToken
     **/
    public String getJobProgressToken() {
        return jobProgressToken;
    }

    public void setJobProgressToken(String jobProgressToken) {
        this.jobProgressToken = jobProgressToken;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobTokenResponse addTeststoCycleresponse = (JobTokenResponse) o;
        return Objects.equals(this.jobProgressToken, addTeststoCycleresponse.jobProgressToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobProgressToken);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobTokenResponse {\n");

        sb.append("    jobProgressToken: ").append(toIndentedString(jobProgressToken)).append("\n");
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
