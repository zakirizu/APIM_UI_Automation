package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UpdateCycleInformationResponse
 */
public class UpdateCycleInformationResponse {
    @JsonProperty("error")
    private String error = null;

    @JsonProperty("success")
    private String success = null;

    @JsonProperty("noPermission")
    private String noPermission = null;

    public UpdateCycleInformationResponse error(String error) {
        this.error = error;
        return this;
    }

    /**
     * Get error
     *
     * @return error
     **/
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public UpdateCycleInformationResponse success(String success) {
        this.success = success;
        return this;
    }

    /**
     * Get success
     *
     * @return success
     **/
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public UpdateCycleInformationResponse noPermission(String noPermission) {
        this.noPermission = noPermission;
        return this;
    }

    /**
     * Get noPermission
     *
     * @return noPermission
     **/
    public String getNoPermission() {
        return noPermission;
    }

    public void setNoPermission(String noPermission) {
        this.noPermission = noPermission;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCycleInformationResponse UpdateCycleInformationResponse = (UpdateCycleInformationResponse) o;
        return Objects.equals(this.error, UpdateCycleInformationResponse.error) &&
                Objects.equals(this.success, UpdateCycleInformationResponse.success) &&
                Objects.equals(this.noPermission, UpdateCycleInformationResponse.noPermission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, success, noPermission);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateCycleInformationResponse {\n");

        sb.append("    error: ").append(toIndentedString(error)).append("\n");
        sb.append("    success: ").append(toIndentedString(success)).append("\n");
        sb.append("    noPermission: ").append(toIndentedString(noPermission)).append("\n");
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