package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetAllProjectsResponse
 */
public class GetAllProjectsResponse {
    @JsonProperty("options")
    private java.util.List<Option> options = new java.util.ArrayList<Option>();

    public GetAllProjectsResponse options(java.util.List<Option> options) {
        this.options = options;
        return this;
    }

    public GetAllProjectsResponse addOptionsItem(Option optionsItem) {
        this.options.add(optionsItem);
        return this;
    }

    /**
     * Get options
     * @return options
     **/
    public java.util.List<Option> getOptions() {
        return options;
    }

    public void setOptions(java.util.List<Option> options) {
        this.options = options;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllProjectsResponse getAllProjectsresponse = (GetAllProjectsResponse) o;
        return Objects.equals(this.options, getAllProjectsresponse.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetAllProjectsResponse {\n");

        sb.append("    options: ").append(toIndentedString(options)).append("\n");
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