package com.informatica.zapi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Execution {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("orderId")
    private Integer orderId = null;

    @JsonProperty("executionStatus")
    private String executionStatus = null;

    @JsonProperty("comment")
    private String comment = null;

    @JsonProperty("htmlComment")
    private String htmlComment = null;

    @JsonProperty("cycleId")
    private Integer cycleId = null;

    @JsonProperty("cycleName")
    private String cycleName = null;

    @JsonProperty("versionId")
    private Integer versionId = null;

    @JsonProperty("versionName")
    private String versionName = null;

    @JsonProperty("projectId")
    private Integer projectId = null;

    @JsonProperty("createdBy")
    private String createdBy = null;

    @JsonProperty("modifiedBy")
    private String modifiedBy = null;

    @JsonProperty("issueId")
    private Integer issueId = null;

    @JsonProperty("issueKey")
    private String issueKey = null;

    @JsonProperty("summary")
    private String summary = null;

    @JsonProperty("label")
    private String label = null;

    @JsonProperty("component")
    private String component = null;

    @JsonProperty("projectKey")
    private String projectKey = null;

    @JsonProperty("executionDefectCount")
    private Integer executionDefectCount = null;

    @JsonProperty("stepDefectCount")
    private Integer stepDefectCount = null;

    @JsonProperty("totalDefectCount")
    private Integer totalDefectCount = null;

    public Execution id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Execution orderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Get orderId
     *
     * @return orderId
     **/
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Execution executionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
        return this;
    }

    /**
     * Get executionStatus
     *
     * @return executionStatus
     **/
    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public Execution comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Get comment
     *
     * @return comment
     **/
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Execution htmlComment(String htmlComment) {
        this.htmlComment = htmlComment;
        return this;
    }

    /**
     * Get htmlComment
     *
     * @return htmlComment
     **/
    public String getHtmlComment() {
        return htmlComment;
    }

    public void setHtmlComment(String htmlComment) {
        this.htmlComment = htmlComment;
    }

    public Execution cycleId(Integer cycleId) {
        this.cycleId = cycleId;
        return this;
    }

    /**
     * Get cycleId
     *
     * @return cycleId
     **/
    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public Execution cycleName(String cycleName) {
        this.cycleName = cycleName;
        return this;
    }

    /**
     * Get cycleName
     *
     * @return cycleName
     **/
    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public Execution versionId(Integer versionId) {
        this.versionId = versionId;
        return this;
    }

    /**
     * Get versionId
     *
     * @return versionId
     **/
    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Execution versionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    /**
     * Get versionName
     *
     * @return versionName
     **/
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Execution projectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     *
     * @return projectId
     **/
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Execution createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Get createdBy
     *
     * @return createdBy
     **/
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Execution modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    /**
     * Get modifiedBy
     *
     * @return modifiedBy
     **/
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Execution issueId(Integer issueId) {
        this.issueId = issueId;
        return this;
    }

    /**
     * Get issueId
     *
     * @return issueId
     **/
    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Execution issueKey(String issueKey) {
        this.issueKey = issueKey;
        return this;
    }

    /**
     * Get issueKey
     *
     * @return issueKey
     **/
    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public Execution summary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * Get summary
     *
     * @return summary
     **/
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Execution label(String label) {
        this.label = label;
        return this;
    }

    /**
     * Get label
     *
     * @return label
     **/
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Execution component(String component) {
        this.component = component;
        return this;
    }

    /**
     * Get component
     *
     * @return component
     **/
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Execution projectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    /**
     * Get projectKey
     *
     * @return projectKey
     **/
    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public Execution executionDefectCount(Integer executionDefectCount) {
        this.executionDefectCount = executionDefectCount;
        return this;
    }

    /**
     * Get executionDefectCount
     *
     * @return executionDefectCount
     **/
    public Integer getExecutionDefectCount() {
        return executionDefectCount;
    }

    public void setExecutionDefectCount(Integer executionDefectCount) {
        this.executionDefectCount = executionDefectCount;
    }

    public Execution stepDefectCount(Integer stepDefectCount) {
        this.stepDefectCount = stepDefectCount;
        return this;
    }

    /**
     * Get stepDefectCount
     *
     * @return stepDefectCount
     **/
    public Integer getStepDefectCount() {
        return stepDefectCount;
    }

    public void setStepDefectCount(Integer stepDefectCount) {
        this.stepDefectCount = stepDefectCount;
    }

    public Execution totalDefectCount(Integer totalDefectCount) {
        this.totalDefectCount = totalDefectCount;
        return this;
    }

    /**
     * Get totalDefectCount
     *
     * @return totalDefectCount
     **/
    public Integer getTotalDefectCount() {
        return totalDefectCount;
    }

    public void setTotalDefectCount(Integer totalDefectCount) {
        this.totalDefectCount = totalDefectCount;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Execution Execution = (Execution) o;
        return Objects.equals(this.id, Execution.id) &&
                Objects.equals(this.orderId, Execution.orderId) &&
                Objects.equals(this.executionStatus, Execution.executionStatus) &&
                Objects.equals(this.comment, Execution.comment) &&
                Objects.equals(this.htmlComment, Execution.htmlComment) &&
                Objects.equals(this.cycleId, Execution.cycleId) &&
                Objects.equals(this.cycleName, Execution.cycleName) &&
                Objects.equals(this.versionId, Execution.versionId) &&
                Objects.equals(this.versionName, Execution.versionName) &&
                Objects.equals(this.projectId, Execution.projectId) &&
                Objects.equals(this.createdBy, Execution.createdBy) &&
                Objects.equals(this.modifiedBy, Execution.modifiedBy) &&
                Objects.equals(this.issueId, Execution.issueId) &&
                Objects.equals(this.issueKey, Execution.issueKey) &&
                Objects.equals(this.summary, Execution.summary) &&
                Objects.equals(this.label, Execution.label) &&
                Objects.equals(this.component, Execution.component) &&
                Objects.equals(this.projectKey, Execution.projectKey) &&
                Objects.equals(this.executionDefectCount, Execution.executionDefectCount) &&
                Objects.equals(this.stepDefectCount, Execution.stepDefectCount) &&
                Objects.equals(this.totalDefectCount, Execution.totalDefectCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, executionStatus, comment, htmlComment, cycleId, cycleName, versionId, versionName, projectId, createdBy, modifiedBy, issueId, issueKey, summary, label, component, projectKey, executionDefectCount, stepDefectCount, totalDefectCount);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Execution {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    executionStatus: ").append(toIndentedString(executionStatus)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    htmlComment: ").append(toIndentedString(htmlComment)).append("\n");
        sb.append("    cycleId: ").append(toIndentedString(cycleId)).append("\n");
        sb.append("    cycleName: ").append(toIndentedString(cycleName)).append("\n");
        sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
        sb.append("    versionName: ").append(toIndentedString(versionName)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
        sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
        sb.append("    issueId: ").append(toIndentedString(issueId)).append("\n");
        sb.append("    issueKey: ").append(toIndentedString(issueKey)).append("\n");
        sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
        sb.append("    label: ").append(toIndentedString(label)).append("\n");
        sb.append("    component: ").append(toIndentedString(component)).append("\n");
        sb.append("    projectKey: ").append(toIndentedString(projectKey)).append("\n");
        sb.append("    executionDefectCount: ").append(toIndentedString(executionDefectCount)).append("\n");
        sb.append("    stepDefectCount: ").append(toIndentedString(stepDefectCount)).append("\n");
        sb.append("    totalDefectCount: ").append(toIndentedString(totalDefectCount)).append("\n");
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

