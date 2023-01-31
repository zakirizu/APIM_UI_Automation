package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateNewCycleResponse
 */
public class CreateNewCycleResponse {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("responseMessage")
    private String responseMessage = null;

    public CreateNewCycleResponse id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateNewCycleResponse responseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    /**
     * Get responseMessage
     *
     * @return responseMessage
     **/
    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateNewCycleResponse CreateNewCycleResponse = (CreateNewCycleResponse) o;
        return Objects.equals(this.id, CreateNewCycleResponse.id) &&
                Objects.equals(this.responseMessage, CreateNewCycleResponse.responseMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responseMessage);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateNewCycleResponse {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    responseMessage: ").append(toIndentedString(responseMessage)).append("\n");
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