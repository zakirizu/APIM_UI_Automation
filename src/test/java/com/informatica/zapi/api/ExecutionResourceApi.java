package com.informatica.zapi.api;

import com.informatica.zapi.ApiException;
import com.informatica.zapi.ApiResponse;
import com.informatica.zapi.GenericType;
import com.informatica.zapi.Pair;
import com.informatica.zapi.ZAPIClient;
import com.informatica.zapi.model.AddTeststoCycleRequest;
import com.informatica.zapi.model.GetExecutionInformationResponse;
import com.informatica.zapi.model.GetListofExecutionResponse;
import com.informatica.zapi.model.JobProgress;
import com.informatica.zapi.model.JobTokenResponse;
import com.informatica.zapi.model.UpdateBulkDefectsRequest;
import com.informatica.zapi.model.UpdateBulkExecutionStatusRequest;

public class ExecutionResourceApi extends AbstractResourceApi {

    public ExecutionResourceApi() {
        super();
    }

    public ExecutionResourceApi(ZAPIClient apiClient) {
        super(apiClient);
    }

    /**
     * Add Tests to Cycle Add Test to Cycle &lt;table&gt;&lt;tr&gt;&lt;td colspan&#x3D;2&gt;This API will execute based
     * on following conditions:&lt;tr&gt;&lt;td&gt;1&lt;td&gt;From individual test required following
     * params:&lt;br&gt;(assigneeType, cycleId, issues, method &#x3D; 1, projectId,
     * versionId)&lt;tr&gt;&lt;td&gt;2&lt;td&gt;From search filter required following params:&lt;br&gt;(assigneeType,
     * cycleId, issues, method &#x3D; 2, projectId, versionId, searchId)&lt;tr&gt;&lt;td&gt;3&lt;td&gt;From another
     * cycle required following params:&lt;br&gt;(assigneeType, cycleId, issues, method &#x3D; 3, projectId, versionId,
     * components, fromCycleId, fromVersionId, hasDefects, labels, priorities, statuses)&lt;/table&gt;&lt;p&gt;This API
     * returns a jobProgressToken which should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;add_tests_to_cycle_job_progress. Once the
     * request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     *
     * @param contentType
     * @param body (required)
     * @return JobTokenResponse
     * @throws ApiException if fails to make API call
     */
    public JobTokenResponse executionAddTestsToCyclePost(String contentType, AddTeststoCycleRequest body)
            throws ApiException {
        return executionAddTestsToCyclePostWithHttpInfo(contentType, body).getData();
    }

    /**
     * Add Tests to Cycle Add Test to Cycle &lt;table&gt;&lt;tr&gt;&lt;td colspan&#x3D;2&gt;This API will execute based
     * on following conditions:&lt;tr&gt;&lt;td&gt;1&lt;td&gt;From individual test required following
     * params:&lt;br&gt;(assigneeType, cycleId, issues, method &#x3D; 1, projectId,
     * versionId)&lt;tr&gt;&lt;td&gt;2&lt;td&gt;From search filter required following params:&lt;br&gt;(assigneeType,
     * cycleId, issues, method &#x3D; 2, projectId, versionId, searchId)&lt;tr&gt;&lt;td&gt;3&lt;td&gt;From another
     * cycle required following params:&lt;br&gt;(assigneeType, cycleId, issues, method &#x3D; 3, projectId, versionId,
     * components, fromCycleId, fromVersionId, hasDefects, labels, priorities, statuses)&lt;/table&gt;&lt;p&gt;This API
     * returns a jobProgressToken which should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;add_tests_to_cycle_job_progress. Once the
     * request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     *
     * @param contentType
     * @param body (required)
     * @return ApiResponse&lt;JobTokenResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<JobTokenResponse> executionAddTestsToCyclePostWithHttpInfo(String contentType,
                                                                                  AddTeststoCycleRequest body)
            throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400,
                "Missing the required parameter 'body' when calling executionAddTestsToCyclePost");
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/execution/addTestsToCycle";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<JobTokenResponse> localVarReturnType = new GenericType<JobTokenResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams,
            localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Update Bulk Defects Update bulk Defect by Executions , Defects &lt;p&gt;This API returns a jobProgressToken which
     * should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;bulk_execution_associate_defect_job_progress.
     * Once the request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     *
     * @param contentType (required)
     * @param body (required)
     * @return JobTokenResponse
     * @throws ApiException if fails to make API call
     */
    public JobTokenResponse executionUpdateWithBulkDefectsPut(String contentType, UpdateBulkDefectsRequest body)
            throws ApiException {
        return executionUpdateWithBulkDefectsPutWithHttpInfo(contentType, body).getData();
    }

    /**
     * Update Bulk Defects Update bulk Defect by Executions , Defects &lt;p&gt;This API returns a jobProgressToken which
     * should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;bulk_execution_associate_defect_job_progress.
     * Once the request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     *
     * @param contentType (required)
     * @param body (required)
     * @return ApiResponse&lt;JobTokenResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<JobTokenResponse> executionUpdateWithBulkDefectsPutWithHttpInfo(String contentType,
                                                                                       UpdateBulkDefectsRequest body)
            throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400,
                "Missing the required parameter 'body' when calling executionUpdateWithBulkDefectsPut");
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/execution/updateWithBulkDefects";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<JobTokenResponse> localVarReturnType = new GenericType<JobTokenResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams,
            localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Update Bulk Execution Status Update bulk Execution Status by Status &lt;p&gt;This API returns a jobProgressToken
     * which should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;update_bulk_execution_status_job_progress.
     * Once the request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     * 
     * @param contentType (required)
     * @param body (required)
     * @return JobTokenResponse
     * @throws ApiException if fails to make API call
     */
    public JobTokenResponse executionUpdateBulkStatusPut(String contentType, UpdateBulkExecutionStatusRequest body)
            throws ApiException {
        return executionUpdateBulkStatusPutWithHttpInfo(contentType, body).getData();
    }

    /**
     * Update Bulk Execution Status Update bulk Execution Status by Status &lt;p&gt;This API returns a jobProgressToken
     * which should be used for making the call to
     * /rest/zapi/latest/execution/jobProgress/:jobProgressToken?type&#x3D;update_bulk_execution_status_job_progress.
     * Once the request is processed, the jobProgress will populate the message field with result.&lt;/p&gt;
     * 
     * @param contentType (required)
     * @param body (required)
     * @return ApiResponse&lt;{@link JobTokenResponse}&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<JobTokenResponse> executionUpdateBulkStatusPutWithHttpInfo(String contentType,
                                                                                  UpdateBulkExecutionStatusRequest body)
            throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400,
                "Missing the required parameter 'body' when calling executionUpdateBulkStatusPut");
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/execution/updateBulkStatus";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<JobTokenResponse> localVarReturnType = new GenericType<JobTokenResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams,
            localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Get Execution Information Gets all executions available for given execution Id
     * 
     * @param id id of Execution (required)
     * @param expand expand of Execution
     * @param contentType
     * @return GetExecutionInformationResponse
     * @throws ApiException if fails to make API call
     */
    public GetExecutionInformationResponse executionByIdGet(Integer id, String expand, String contentType)
            throws ApiException {
        return executionByIdGetWithHttpInfo(id, expand, contentType).getData();
    }

    /**
     * Get Execution Information Gets all executions available for given execution Id
     * 
     * @param id id of Execution (required)
     * @param expand expand of Execution
     * @param contentType
     * @return ApiResponse&lt;GetExecutionInformationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetExecutionInformationResponse>
            executionByIdGetWithHttpInfo(Integer id, String expand, String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException(400, "Missing the required parameter 'id' when calling executionByIdGet");
        }

        // verify the required parameter 'expand' is set
        // if (expand == null) {
        // throw new ApiException(400, "Missing the required parameter 'expand' when calling executionByIdGet");
        // }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/execution/{id}"
            .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand", expand));

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<GetExecutionInformationResponse> localVarReturnType = new GenericType<GetExecutionInformationResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
            localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    // private long CALL_DELAY = 100l;

    public JobProgress getJobProgress(String jobProgressToken) throws ApiException {
        Object localVarPostBody = null;
        String localVarPath = "/zapi/latest/execution/jobProgress/{token}".replaceAll("\\{" + "token" + "\\}",
            apiClient.escapeString(jobProgressToken));
        JobProgress jobProgress;
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarHeaderParams.put("Content-Type", apiClient.parameterToString("application/json"));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<JobProgress> localVarReturnType = new GenericType<JobProgress>() {
        };

        try {
            do {
                // Thread.sleep(CALL_DELAY);

                jobProgress = apiClient
                    .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                        localVarFormParams, localVarAccept, localVarContentType, localVarReturnType)
                    .getData();

            } while (jobProgress.progress != null && !jobProgress.progress.equals(1.0));

            return jobProgress;
        } catch (Throwable exception) {
            throw new ApiException(exception);
        }
    }

    public GetListofExecutionResponse executionGet(Integer issueId, Long projectId, Long versionId, Integer cycleId,
                                                   Integer offset, String action, String sorter, String expand,
                                                   Integer limit, Long folderId, String contentType)
            throws ApiException {
        return executionGetWithHttpInfo(issueId, projectId, versionId, cycleId, offset, action, sorter, expand, limit,
            folderId, contentType).getData();
    }

    public ApiResponse<GetListofExecutionResponse>
            executionGetWithHttpInfo(Integer issueId, Long projectId, Long versionId, Integer cycleId, Integer offset,
                                     String action, String sorter, String expand, Integer limit, Long folderId,
                                     String contentType)
                    throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new ApiException(400, "Missing the required parameter 'projectId' when calling executionGet");
        }

        // verify the required parameter 'versionId' is set
        if (versionId == null) {
            throw new ApiException(400, "Missing the required parameter 'versionId' when calling executionGet");
        }

        // verify the required parameter 'cycleId' is set
        if (cycleId == null) {
            throw new ApiException(400, "Missing the required parameter 'cycleId' when calling executionGet");
        }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/execution";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (null != issueId)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "issueId", issueId));

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "projectId", projectId));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "versionId", versionId));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "cycleId", cycleId));

        if (null != offset)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
        if (null != action)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "action", action));
        if (null != sorter)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "sorter", sorter));
        if (null != expand)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand", expand));
        if (null != limit)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (null != folderId)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "folderId", folderId));

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<GetListofExecutionResponse> localVarReturnType = new GenericType<GetListofExecutionResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
            localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }
}
