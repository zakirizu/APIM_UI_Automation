package com.informatica.zapi.model;

import java.util.ArrayList;
import java.util.List;

public class GetListofExecutionResponse {

    private List<Execution> executions = new ArrayList<>(0);
    private Boolean isTimeTrackingEnabled;
    private Boolean isExecutionWorkflowEnabledForProject;
    private Integer executionsToBeLogged;
    private String totalExecutionLoggedTime;
    private String totalExecutionEstimatedTime;
    private Integer recordsCount;
    private Integer totalExecutions;
    private Integer totalExecuted;
    private String currentlySelectedExecutionId;

    public List<Execution> getExecutions() {
        return executions;
    }

    public void addExecution(Execution execution) {
        this.getExecutions().add(execution);
    }

//    public void setExecutions(List<Execution> executions) {
//        this.executions = executions;
//    }

    public Boolean getTimeTrackingEnabled() {
        return isTimeTrackingEnabled;
    }

    public void setTimeTrackingEnabled(Boolean timeTrackingEnabled) {
        isTimeTrackingEnabled = timeTrackingEnabled;
    }

    public Boolean getExecutionWorkflowEnabledForProject() {
        return isExecutionWorkflowEnabledForProject;
    }

    public void setExecutionWorkflowEnabledForProject(Boolean executionWorkflowEnabledForProject) {
        isExecutionWorkflowEnabledForProject = executionWorkflowEnabledForProject;
    }

    public Integer getExecutionsToBeLogged() {
        return executionsToBeLogged;
    }

    public void setExecutionsToBeLogged(Integer executionsToBeLogged) {
        this.executionsToBeLogged = executionsToBeLogged;
    }

    public String getTotalExecutionLoggedTime() {
        return totalExecutionLoggedTime;
    }

    public void setTotalExecutionLoggedTime(String totalExecutionLoggedTime) {
        this.totalExecutionLoggedTime = totalExecutionLoggedTime;
    }

    public String getTotalExecutionEstimatedTime() {
        return totalExecutionEstimatedTime;
    }

    public void setTotalExecutionEstimatedTime(String totalExecutionEstimatedTime) {
        this.totalExecutionEstimatedTime = totalExecutionEstimatedTime;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    public Integer getTotalExecutions() {
        return totalExecutions;
    }

    public void setTotalExecutions(Integer totalExecutions) {
        this.totalExecutions = totalExecutions;
    }

    public Integer getTotalExecuted() {
        return totalExecuted;
    }

    public void setTotalExecuted(Integer totalExecuted) {
        this.totalExecuted = totalExecuted;
    }

    public String getCurrentlySelectedExecutionId() {
        return currentlySelectedExecutionId;
    }

    public void setCurrentlySelectedExecutionId(String currentlySelectedExecutionId) {
        this.currentlySelectedExecutionId = currentlySelectedExecutionId;
    }
}
