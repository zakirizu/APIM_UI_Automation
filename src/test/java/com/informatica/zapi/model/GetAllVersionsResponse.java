package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetAllVersionsResponse
 */
public class GetAllVersionsResponse {
    @JsonProperty("type")
    private String type = null;

    @JsonProperty("hasAccessToSoftware")
    private String hasAccessToSoftware = null;

    @JsonProperty("unreleasedVersions")
    private java.util.List<ListVersion> unreleasedVersions = new java.util.ArrayList<ListVersion>();

    @JsonProperty("releasedVersions")
    private java.util.List<ListVersion> releasedVersions = new java.util.ArrayList<ListVersion>();

    public GetAllVersionsResponse type(String type) {
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

    public GetAllVersionsResponse hasAccessToSoftware(String hasAccessToSoftware) {
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

    public GetAllVersionsResponse unreleasedVersions(java.util.List<ListVersion> listVersions) {
        this.unreleasedVersions = listVersions;
        return this;
    }

    public GetAllVersionsResponse addUnreleasedVersionsItem(ListVersion versionsItem) {
        this.unreleasedVersions.add(versionsItem);
        return this;
    }

    /**
     * Get unreleasedVersions
     * @return unreleasedVersions
     **/
    public java.util.List<ListVersion> getUnreleasedVersions() {
        return unreleasedVersions;
    }

    public void setUnreleasedVersions(java.util.List<ListVersion> unreleasedVersions) {
        this.unreleasedVersions = unreleasedVersions;
    }

    public GetAllVersionsResponse releasedVersions(java.util.List<ListVersion> releasedVersions) {
        this.releasedVersions = releasedVersions;
        return this;
    }

    public GetAllVersionsResponse addReleasedVersionsItem(ListVersion releasedVersionsItem) {
        this.releasedVersions.add(releasedVersionsItem);
        return this;
    }

    /**
     * Get releasedVersions
     * @return releasedVersions
     **/
    public java.util.List<ListVersion> getReleasedVersions() {
        return releasedVersions;
    }

    public void setReleasedVersions(java.util.List<ListVersion> releasedVersions) {
        this.releasedVersions = releasedVersions;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllVersionsResponse getAllVersionsresponse = (GetAllVersionsResponse) o;
        return Objects.equals(this.type, getAllVersionsresponse.type) &&
                Objects.equals(this.hasAccessToSoftware, getAllVersionsresponse.hasAccessToSoftware) &&
                Objects.equals(this.unreleasedVersions, getAllVersionsresponse.unreleasedVersions) &&
                Objects.equals(this.releasedVersions, getAllVersionsresponse.releasedVersions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, hasAccessToSoftware, unreleasedVersions, releasedVersions);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetAllVersionsResponse {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    hasAccessToSoftware: ").append(toIndentedString(hasAccessToSoftware)).append("\n");
        sb.append("    unreleasedVersions: ").append(toIndentedString(unreleasedVersions)).append("\n");
        sb.append("    releasedVersions: ").append(toIndentedString(releasedVersions)).append("\n");
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
