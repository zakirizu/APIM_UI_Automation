package com.informatica.zapi.model;

import java.util.List;

public class JobProgress {

    public String timeTaken;
    public String stepMessage;
    public String summaryMessage;
    public List<String> stepMessages;
    public Double progress;
    public String message;
    public String errorMessage;
    public String stepLabel;
    public Integer totalSteps;
    public Integer completedSteps;
    public String entityId;
    public String id;
    public String entity;

    public JobProgress() {
    }

    @Override
    public String toString() {
        return "JobProgress{" +
                "timeTaken='" + timeTaken + '\'' +
                ", stepMessage='" + stepMessage + '\'' +
                ", summaryMessage='" + summaryMessage + '\'' +
                ", totalSteps='" + totalSteps + '\'' +
                ", entityId='" + entityId + '\'' +

                ", stepMessages=" + stepMessages +
                ", progress=" + progress +
                ", message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", stepLabel='" + stepLabel + '\'' +
                '}';
    }
}
