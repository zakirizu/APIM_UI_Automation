package com.informatica.bo;

public class TestInfo {

	private Boolean isRun;
	
	private String testMethodName;
	
	private String description;
	
	private String additionalDetails;
	
	private String jirakey;
	
	public TestInfo(Boolean isRun, String testMethodName, String description,String additionalDetails,String jirakey) {
		this.isRun = isRun;
		this.testMethodName = testMethodName;
		this.description = description;
		this.additionalDetails = additionalDetails;
		this.jirakey = jirakey;
	}

	public Boolean getIsRun() {
		return isRun;
	}

	public void setIsRun(Boolean isRun) {
		this.isRun = isRun;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getjirakey() {
		return jirakey;
	}

	public void setjirakey(String jirakey) {
		this.jirakey = jirakey;
	}



}
