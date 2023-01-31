package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ListVersion
 */
public class ListVersion {
    @JsonProperty("value")
    private String value = null;

    @JsonProperty("archived")
    private Boolean archived = null;

    @JsonProperty("label")
    private String label = null;

    public ListVersion value(String value) {
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

    public ListVersion archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    /**
     * Get archived
     * @return archived
     **/
    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public ListVersion label(String label) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListVersion listVersion = (ListVersion) o;
        return Objects.equals(this.value, listVersion.value) &&
                Objects.equals(this.archived, listVersion.archived) &&
                Objects.equals(this.label, listVersion.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, archived, label);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ListVersion {\n");

        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    archived: ").append(toIndentedString(archived)).append("\n");
        sb.append("    label: ").append(toIndentedString(label)).append("\n");
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
