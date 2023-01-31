package com.informatica.zapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.informatica.zapi.api.CycleResourceApi;
import com.informatica.zapi.api.ExecutionResourceApi;
import com.informatica.zapi.api.JiraTestCaseResourceApi;
import com.informatica.zapi.api.UtilResourceApi;
import com.informatica.zapi.model.AddTeststoCycleRequest;
import com.informatica.zapi.model.CreateNewCycleRequest;
import com.informatica.zapi.model.CreateNewCycleResponse;
import com.informatica.zapi.model.Cycle;
import com.informatica.zapi.model.Execution;
import com.informatica.zapi.model.ExecutionStatusEnum;
import com.informatica.zapi.model.GetAllProjectsResponse;
import com.informatica.zapi.model.GetAllVersionsResponse;
import com.informatica.zapi.model.GetListofCycleResponse;
import com.informatica.zapi.model.GetListofExecutionResponse;
import com.informatica.zapi.model.JobProgress;
import com.informatica.zapi.model.JobTokenResponse;
import com.informatica.zapi.model.ListVersion;
import com.informatica.zapi.model.Option;
import com.informatica.zapi.model.SortOrder;
import com.informatica.zapi.model.UpdateBulkExecutionStatusRequest;
import com.informatica.zapi.model.UpdateCycleInformationRequest;
import com.informatica.zapi.model.UpdateCycleInformationResponse;

//import org.slf4j.LoggerFactory;

public class ZAPIUtil {

    final String contentType = "application/json";

    private String projectId = null;
    private String versionId = null;
    private final String version;
    private final String cycleName;
    private final String projectName;
    private final String Env_name;
    private Cycle cycle = new Cycle();
    
    private Map<String, Execution> testExecutionMap = new HashMap<>(0);

    private DateFormat df = new SimpleDateFormat("dd/MMM/yy");

    private final ZAPIClient zapiClient = Configuration.getDefaultApiClient()
            .setBasePath("https://infajira.informatica.com/rest").setConnectTimeout(600000).setReadTimeout(600000);

    private final UtilResourceApi api = new UtilResourceApi(zapiClient);
    private final CycleResourceApi cycleResourceApi = new CycleResourceApi(zapiClient);
    private final JiraTestCaseResourceApi jiraTestCaseResourceApi = new JiraTestCaseResourceApi(zapiClient);
    private final ExecutionResourceApi executionResourceApi = new ExecutionResourceApi(zapiClient);

    private ZAPIUtil() {
        zapiClient.setUsername(System.getProperty("jira.username"));
        zapiClient.setPassword(System.getProperty("jira.password"));

        projectName = System.getProperty("PROJECT_NAME");
        version = System.getProperty("PROJECT_VERSION");
        Env_name = System.getProperty("Env_name");
        cycleName = System.getProperty("jira.cycle");
     

       
        List<Option> result = null;
        try {
            GetAllProjectsResponse response = api.utilProjectListGet(contentType);
            String str = zapiClient.mapper.writeValueAsString(response);
          //  LoggerFactory.getLogger(getClass()).info(str);
            result = response.getOptions().stream().filter(option -> (option.getLabel().equalsIgnoreCase(projectName)))
                    .collect(Collectors.toList());
        } catch (ApiException e) {

        } catch (JsonProcessingException e) {

        }

        if (null != result && !result.isEmpty()) {
            projectId = result.get(0).getValue();
        }
        List<ListVersion> versions = new ArrayList<>(0);
        try {
            GetAllVersionsResponse response = api.utilVersionBoardListGet(String.valueOf(projectId), contentType);
           // LoggerFactory.getLogger(getClass()).info(zapiClient.mapper.writeValueAsString(response));
            versions.addAll(response.getUnreleasedVersions().stream()
                    .filter(option -> (option.getLabel().equalsIgnoreCase(version))).collect(Collectors.toList()));

            versions.addAll(response.getReleasedVersions().stream()
                    .filter(option -> (option.getLabel().equalsIgnoreCase(version))).collect(Collectors.toList()));
        } catch (ApiException e) {
        	

		 } 
		 //catch (JsonProcessingException e) {

        // }
        catch(Exception e) {
        	e.printStackTrace();
        }
        //System.out.println(versions.toString());
        if (null != versions && !versions.isEmpty()) {
            versionId = versions.get(0).getValue();
        }
    }

    private static class InstanceHandler {
        private static final ZAPIUtil UTIL = new ZAPIUtil();
    }

    public static ZAPIUtil getInstance() {
        return InstanceHandler.UTIL;
    }

    public void getOrCreateCycle(String name) throws ApiException {
		boolean isCreate = true;
		
		if (null == cycleName) {
			isCreate = true;
		} 

		if (isCreate) {
			GetListofCycleResponse cycleresponse = cycleResourceApi.cycleGet(Long.parseLong(projectId),
			Long.parseLong(versionId), null, null, null, "executionSummaries", null);
			int curr_iteration = 0;
			List<Cycle> cycles = cycleresponse.getcycles();
			
			for (Cycle cycle : cycles) {
				if (cycle.getName().startsWith(name)) {
					int iter = 0;
					try {
						iter = Integer.parseInt(cycle.getName().substring(cycle.getName().lastIndexOf("-") + 1));
						// System.out.println("cycle.getName()"+cycle.getName());
					} catch (NumberFormatException e) {

					}
					if (iter > curr_iteration) {
						curr_iteration = iter;
					}
				}
			}
			
			String newCycleName = (new StringBuilder(name)).append("-").append(curr_iteration + 1).toString();
			CreateNewCycleRequest createNewCyclerequest = new CreateNewCycleRequest();
			createNewCyclerequest.setProjectId(projectId);
			createNewCyclerequest.setVersionId(versionId);
			createNewCyclerequest.setName(newCycleName);
			createNewCyclerequest.setDescription("New Cycle created by automation");
			createNewCyclerequest.setEnvironment(Env_name);
		
			createNewCyclerequest.setStartDate(df.format(new Date(System.currentTimeMillis())));
			CreateNewCycleResponse cycleResource = cycleResourceApi.cyclePost(null, createNewCyclerequest);
			
			GetListofCycleResponse cycleresponse1 = cycleResourceApi.cycleGet(Long.parseLong(projectId),
					Long.parseLong(versionId), Long.parseLong(cycleResource.getId()), null, null, "executionSummaries",
					null);
			this.cycle = cycleresponse1.getcycles().get(0);

		} else {

			GetListofCycleResponse cycleresponse = cycleResourceApi.cycleGet(Long.parseLong(projectId),
					Long.parseLong(versionId), null, null, null, "executionSummaries", null);
			List<Cycle> cycles = cycleresponse.getcycles();
			for (Cycle cycle : cycles) {
				if (cycle.getName().equals(cycleName)) {
					this.cycle = cycle;
				}
			}
		
			if (java.util.Objects.isNull(cycle.getStartDate())) {
				cycle.setStartDate(df.format(new Date(System.currentTimeMillis())));
			}
			UpdateCycleInformationRequest updateCycleInformationrequest = new UpdateCycleInformationRequest();
			updateCycleInformationrequest.setId(String.valueOf(cycle.getId()));
			updateCycleInformationrequest.setVersionId(versionId);
			updateCycleInformationrequest.setName(cycle.getName());
			updateCycleInformationrequest.setDescription(cycle.getDescription());
			updateCycleInformationrequest.setEnvironment(cycle.getEnvironment());;

			updateCycleInformationrequest.setStartDate(cycle.getStartDate());
			updateCycleInformationrequest.setEndDate(cycle.getStartDate());

			UpdateCycleInformationResponse updateCycleInformationresponse = cycleResourceApi.cyclePut(contentType,
					updateCycleInformationrequest);
			
			GetListofCycleResponse cycleresponse1 = cycleResourceApi.cycleGet(Long.parseLong(projectId),
					Long.parseLong(versionId), new Long(cycle.getId()), null, null, "executionSummaries", null);
			this.cycle = cycleresponse1.getcycles().get(0);

		}
	}

	int offset = 0;
	int size = 40;
	String sortBy = "status";
	SortOrder sortOrder = SortOrder.ASC;

	public void addTests(List<String> testNames) throws ApiException {

		AddTeststoCycleRequest addTeststoCyclerequest = new AddTeststoCycleRequest();
		addTeststoCyclerequest.setCycleId(String.valueOf(cycle.getId()));
		addTeststoCyclerequest.setProjectId(projectId);
		addTeststoCyclerequest.setVersionId(versionId);
		addTeststoCyclerequest.setIssues(testNames);
		addTeststoCyclerequest.setMethod("1");
		addTeststoCyclerequest.hasDefects(false);

		try {
			JobTokenResponse result = executionResourceApi.executionAddTestsToCyclePost(contentType,
					addTeststoCyclerequest);
			JobProgress progress = executionResourceApi.getJobProgress(result.getJobProgressToken());
			// log.info("Added the testcases" + result.toString());
		} catch (ApiException e) {
			// log.error(e.getMessage(), e);
		}
		GetListofExecutionResponse executionResponse = executionResourceApi.executionGet(null,
				Long.parseLong(projectId), Long.parseLong(versionId), cycle.getId(), offset, null, "issueKey", null,
				size, null, null);

		List<Execution> executions = executionResponse.getExecutions();

		while (executions.size() < executionResponse.getTotalExecutions()) {
			executionResponse = executionResourceApi.executionGet(null, Long.parseLong(projectId),
					Long.parseLong(versionId), cycle.getId(), executions.size(), null, "issueKey", null, size, null,
					null);
			executions.addAll(executionResponse.getExecutions());
		}

		for (Execution execution : executions) {
			// String testName =
			// issueIdTestMap.get(String.valueOf(execution.getIssueId()));
			//
			testExecutionMap.put(execution.getIssueKey(), execution);
		}
	}

	public void updateExecution(String testName, ExecutionStatusEnum status) throws ApiException {

		Execution execution = testExecutionMap.get(testName);
		List<String> executions = new ArrayList<>(1);
		executions.add(String.valueOf(execution.getId()));
		UpdateBulkExecutionStatusRequest updateBulkExecutionStatusrequest = new UpdateBulkExecutionStatusRequest();
		updateBulkExecutionStatusrequest.setExecutions(executions);
		updateBulkExecutionStatusrequest.setStatus(String.valueOf(status.getId()));
		JobTokenResponse responseExecution = executionResourceApi.executionUpdateBulkStatusPut(contentType,
				updateBulkExecutionStatusrequest);
		// JobProgress progress =
		// executionResourceApi.getJobProgress(responseExecution.getJobProgressToken());
		System.out.println(responseExecution.toString());

	}

	public void updateExecution(List<String> testNames, ExecutionStatusEnum status) throws ApiException {
		if (Objects.isNull(testNames)) {
			System.out.println("Is nULL");
			return;
		}
		try {
		List<String> executions = new ArrayList<>(testNames.size());
		testNames.forEach(testName -> {
			Execution execution = testExecutionMap.get(testName);
			executions.add(String.valueOf(execution.getId()));
		});
	

		if (!executions.isEmpty()) {
			UpdateBulkExecutionStatusRequest updateBulkExecutionStatusrequest = new UpdateBulkExecutionStatusRequest();
			updateBulkExecutionStatusrequest.setExecutions(executions);
			updateBulkExecutionStatusrequest.setStatus(String.valueOf(status.getId()));
			JobTokenResponse responseExecution = executionResourceApi.executionUpdateBulkStatusPut(contentType,
					updateBulkExecutionStatusrequest);
			// JobProgress progress =
			// executionResourceApi.getJobProgress(responseExecution.getJobProgressToken());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCycleEndDate() throws ApiException {

		UpdateCycleInformationRequest updateCycleInformationrequest = new UpdateCycleInformationRequest();
		updateCycleInformationrequest.setId(String.valueOf(cycle.getId()));
		updateCycleInformationrequest.setEndDate(df.format(new Date(System.currentTimeMillis())));
		updateCycleInformationrequest.setBuild(cycle.getBuild());
		updateCycleInformationrequest.setStartDate(cycle.getStartDate());
		updateCycleInformationrequest.setDescription(cycle.getDescription());
		updateCycleInformationrequest.setName(cycle.getName());
		UpdateCycleInformationResponse updateCycleInformationresponse = cycleResourceApi.cyclePut(contentType,
				updateCycleInformationrequest);
		// System.out.println(updateCycleInformationresponse);
	}

	
}