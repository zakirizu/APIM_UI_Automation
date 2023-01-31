package com.informatica.web.util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.informatica.tests.BaseTest;
import com.informatica.zapi.ApiException;
import com.informatica.zapi.ZAPIUtil;
import com.informatica.zapi.model.ExecutionStatusEnum;

public class JIRAUpdate {

	private static HashMap<String, String> MethodtoJiraKeymapper = new HashMap<String, String>();
	private static HashMap<String, String> MethodExectuinStatus = new HashMap<String, String>();
	private static HashMap<String, String> JiraExecutionMapping = new HashMap<String, String>();
	private static List<String> Pass_Methods = new ArrayList<String>();
	private static List<String> Fail_Methods = new ArrayList<String>();

	String JIRA_FLAG = ConfigPropertiesLoader.getProperty("JIRA_UPDATE");

	public void updateJIRA() throws ApiException {

		if (JIRA_FLAG.equalsIgnoreCase("Yes") || JIRA_FLAG.equalsIgnoreCase("Y") || JIRA_FLAG.equalsIgnoreCase("y")) {

			MethodtoJiraKeymapper = TestNGBuilder.getMethodtoJiraKeyMapping();
			MethodExectuinStatus = BaseTest.getUpdateMethods_Execution();

			MappingJirakeywithExecutionstatus();
			MethodToPreapareBulkUpdate();

			LoadZAPI();

			Update_JIRA_Executions(Pass_Methods, ExecutionStatusEnum.PASS);
			Update_JIRA_Executions(Fail_Methods, ExecutionStatusEnum.FAIL);

			saveJIRAResult();
			System.out.println("Created and Updated the Execution Status in Zephyr");			

		}
		
	}

	private void MappingJirakeywithExecutionstatus() {

		for (Map.Entry<String, String> entry : MethodtoJiraKeymapper.entrySet()) {

			for (Map.Entry<String, String> entry1 : MethodExectuinStatus.entrySet()) {

				if (entry.getKey().equalsIgnoreCase(entry1.getKey()) && !entry.getValue().equals("-"))
					Method_JIRA_Excel(entry.getValue(), entry1.getValue());

			}

		}
	}

	private void MethodToPreapareBulkUpdate() {

		/*
		 * System.out.println("JIRA And Method Mapping from excel Sheet");
		 * JiraExecutionMapping.entrySet().forEach(entry -> {
		 * System.out.println(entry.getKey() + " " + entry.getValue()); });
		 */

		JiraExecutionMapping.entrySet().forEach(entry -> {
			if (entry.getValue().equalsIgnoreCase("PASS")) {
				Pass_Methods.add(entry.getKey());
			} else {
				Fail_Methods.add(entry.getKey());
			}

		});

		/*
		 * System.out.println("Pass Methods"); Pass_Methods.forEach((n) ->
		 * System.out.println(n)); System.out.println("Fail Methods");
		 * Fail_Methods.forEach((n) -> System.out.println(n));
		 */

	}

	private void Method_JIRA_Excel(String Keys, String ExecutionStatus) {
		String[] params = null;

		if (Keys.contains(",")) {
			params = Keys.split(",");

			for (int j = 0; j < params.length; j++) {
				JiraExecutionMapping.put(params[j], ExecutionStatus);
			}

		} else {
			JiraExecutionMapping.put(Keys, ExecutionStatus);
		}

	}

	private void Update_JIRA_Executions(List<String> Key, ExecutionStatusEnum status) throws ApiException {

		if (!Key.isEmpty()) {
			ZAPIUtil.getInstance().addTests(Key);
			ZAPIUtil.getInstance().updateExecution(Key, status);
		}

	}

	private void LoadZAPI() throws ApiException {
	

			ZAPIUtil.getInstance().getOrCreateCycle(ConfigPropertiesLoader.getProperty("CYCLE_NAME")+"_"+ConfigPropertiesLoader.getProperty("Env_name"));

		
	}

	/*
	 * public void AddJIRA_Keys(List<String> Key, String Jkey) { if
	 * (JIRA_FLAG.equalsIgnoreCase("Yes")) {
	 * 
	 * if (!(JIRA_KEY.equals("-"))) { Key.add(Jkey); }
	 * 
	 * } }
	 */
	
	public void saveJIRAResult() {
		try {
			FileWriter writer = new FileWriter("JIRAResultDetails.txt");
			writer.write("Below are the Passed test cases: " + "\n");
			for (String str : Pass_Methods)
				writer.write(str + "\n");
			writer.write("Below are the failed test cases: " + "\n");
			for (String str : Fail_Methods)
				writer.write(str + "\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
