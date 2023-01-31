package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {
    @JsonProperty("hasAccessToSoftware")
    private String hasAccessToSoftware = null;

    @JsonProperty("label")
    private String label = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("value")
    private String value = null;

    public Option hasAccessToSoftware(String hasAccessToSoftware) {
        this.hasAccessToSoftware = hasAccessToSoftware;
        return this;
    }

    /**
     * Get hasAccessToSoftware
     * @return hasAccessToSoftware
     **/
    public String getHasAccessToSoftware() {
        return hasAccessToSoftware;
    }

    public void setHasAccessToSoftware(String hasAccessToSoftware) {
        this.hasAccessToSoftware = hasAccessToSoftware;
    }

    public Option label(String label) {
        this.label = label;
        return this;
    }

    /**
     * Get label
     * @return label
     **/
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Option type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Option value(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     * @return value
     **/
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return Objects.equals(this.hasAccessToSoftware, option.hasAccessToSoftware) &&
                Objects.equals(this.label, option.label) &&
                Objects.equals(this.type, option.type) &&
                Objects.equals(this.value, option.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasAccessToSoftware, label, type, value);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Option {\n");

        sb.append("    hasAccessToSoftware: ").append(toIndentedString(hasAccessToSoftware)).append("\n");
        sb.append("    label: ").append(toIndentedString(label)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
