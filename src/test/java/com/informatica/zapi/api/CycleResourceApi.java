package com.informatica.zapi.api;

import com.informatica.zapi.ApiException;
import com.informatica.zapi.ApiResponse;
import com.informatica.zapi.GenericType;
import com.informatica.zapi.Pair;
import com.informatica.zapi.ZAPIClient;
import com.informatica.zapi.model.CreateNewCycleRequest;
import com.informatica.zapi.model.CreateNewCycleResponse;
import com.informatica.zapi.model.GetCycleInformationResponse;
import com.informatica.zapi.model.GetListofCycleResponse;
import com.informatica.zapi.model.UpdateCycleInformationRequest;
import com.informatica.zapi.model.UpdateCycleInformationResponse;

public class CycleResourceApi extends AbstractResourceApi {

    public CycleResourceApi() {
        super();
    }

    public CycleResourceApi(ZAPIClient apiClient) {
        super(apiClient);
    }

    /**
     * Get Cycle Information
     * Get Cycle data by Cycle Id. If cycleId -1 is passed, system returns hardcoded cycle
     *
     * @param id          id of Cycle (required)
     * @param contentType
     * @return GetCycleInformationResponse
     * @throws ApiException if fails to make API call
     */
    public GetCycleInformationResponse cycleByIdGet(Long id, String contentType) throws ApiException {
        return cycleByIdGetWithHttpInfo(id, contentType).getData();
    }

    /**
     * Get Cycle Information
     * Get Cycle data by Cycle Id. If cycleId -1 is passed, system returns hardcoded cycle
     *
     * @param id          id of Cycle (required)
     * @param contentType
     * @return ApiResponse&lt;GetCycleInformationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetCycleInformationResponse> cycleByIdGetWithHttpInfo(Long id, String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException(400, "Missing the required parameter 'id' when calling cycleByIdGet");
        }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/cycle/{id}"
                .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

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


        GenericType<GetCycleInformationResponse> localVarReturnType = new GenericType<GetCycleInformationResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Get List of Cycle
     * Get List of Cycle by Project Id
     *
     * @param projectId   projectId of Cycle (required)
     * @param versionId   versionId of Cycle (required)
     * @param id          id of Cycle (required)
     * @param offset      offset of Cycle (required)
     * @param issueId     issueId of Cycle (required)
     * @param expand      expand of Cycle (required)
     * @param contentType
     * @return GetListofCycleResponse
     * @throws ApiException if fails to make API call
     */
    public GetListofCycleResponse cycleGet(Long projectId, Long versionId, Long id, Integer offset, String issueId, String expand, String contentType) throws ApiException {
        return cycleGetWithHttpInfo(projectId, versionId, id, offset, issueId, expand, contentType).getData();
    }

    /**
     * Get List of Cycle
     * Get List of Cycle by Project Id
     *
     * @param projectId   projectId of Cycle (required)
     * @param versionId   versionId of Cycle (required)
     * @param id          id of Cycle
     * @param offset      offset of Cycle
     * @param issueId     issueId of Cycle
     * @param expand      expand of Cycle - possible values "executionSummary"
     * @param contentType
     * @return ApiResponse&lt;GetListofCycleResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetListofCycleResponse> cycleGetWithHttpInfo(Long projectId, Long versionId, Long id, Integer offset, String issueId, String expand, String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new ApiException(400, "Missing the required parameter 'projectId' when calling cycleGet");
        }

        // verify the required parameter 'versionId' is set
        if (versionId == null) {
            throw new ApiException(400, "Missing the required parameter 'versionId' when calling cycleGet");
        }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/cycle";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "projectId", projectId));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "versionId", versionId));
        if (null != id)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "id", id));
        if (null != offset)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
        if (null != issueId)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "issueId", issueId));
        if (null != expand)
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


        GenericType<GetListofCycleResponse> localVarReturnType = new GenericType<GetListofCycleResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Create New Cycle
     * Create New Cycle by given Cycle Information
     *
     * @param contentType
     * @param body        (required)
     * @return CreateNewCycleResponse
     * @throws ApiException if fails to make API call
     */
    public CreateNewCycleResponse cyclePost(String contentType, CreateNewCycleRequest body) throws ApiException {
        return cyclePostWithHttpInfo(contentType, body).getData();
    }

    /**
     * Create New Cycle
     * Create New Cycle by given Cycle Information
     *
     * @param contentType
     * @param body        (required)
     * @return ApiResponse&lt;CreateNewCycleResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<CreateNewCycleResponse> cyclePostWithHttpInfo(String contentType, CreateNewCycleRequest body) throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling cyclePost");
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/cycle";

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


        GenericType<CreateNewCycleResponse> localVarReturnType = new GenericType<CreateNewCycleResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Update Cycle Information
     * Update Cycle Information
     *
     * @param contentType
     * @param body        (required)
     * @return UpdateCycleInformationResponse
     * @throws ApiException if fails to make API call
     */
    public UpdateCycleInformationResponse cyclePut(String contentType, UpdateCycleInformationRequest body) throws ApiException {
        return cyclePutWithHttpInfo(contentType, body).getData();
    }

    /**
     * Update Cycle Information
     * Update Cycle Information
     *
     * @param contentType
     * @param body        (required)
     * @return ApiResponse&lt;UpdateCycleInformationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<UpdateCycleInformationResponse> cyclePutWithHttpInfo(String contentType, UpdateCycleInformationRequest body) throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling cyclePut");
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/cycle";

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


        GenericType<UpdateCycleInformationResponse> localVarReturnType = new GenericType<UpdateCycleInformationResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }
}