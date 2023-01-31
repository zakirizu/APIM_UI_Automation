package com.informatica.bo;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo {

	private boolean isRun;
	
	private String moduleName;
	
	private String description;
	
	private String moduleClassName;
	
	private List<TestInfo> testInfo = new ArrayList<>();
	
	public ModuleInfo(boolean isRun, String moduleName, String moduleImplClassName, String description) {
		this.isRun = isRun;
		this.moduleName = moduleName;
		this.moduleClassName = moduleImplClassName;
		this.description = description;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TestInfo> getTestInfo() {
		return testInfo;
	}

	public void setTestInfo(List<TestInfo> testInfo) {
		this.testInfo = testInfo;
	}

	public String getModuleClassName() {
		return moduleClassName;
	}

	public void setModuleClassName(String moduleClassName) {
		this.moduleClassName = moduleClassName;
	}
	
}
