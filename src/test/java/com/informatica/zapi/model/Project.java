package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Project {
    @JsonProperty("id")
    String id;
    @JsonProperty("key")
    String key;
    @JsonProperty("name")
    String name;
    @JsonProperty("avatarUrls")
    Map<String, String> avatarUrls;
    @JsonProperty("issuetypes")
    List<IssueType> issuetypes = new ArrayList<>();

    public Project(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(Map<String, String> avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public List<IssueType> getIssuetypes() {
        return issuetypes;
    }

    public void setIssuetypes(List<IssueType> issuetypes) {
        this.issuetypes = issuetypes;
    }
}
