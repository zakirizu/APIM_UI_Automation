package com.informatica.zapi.api;

import com.informatica.zapi.ApiException;
import com.informatica.zapi.ApiResponse;
import com.informatica.zapi.GenericType;
import com.informatica.zapi.Pair;
import com.informatica.zapi.ZAPIClient;
import com.informatica.zapi.model.GetAllProjectsResponse;
import com.informatica.zapi.model.GetAllVersionsResponse;

public class UtilResourceApi extends AbstractResourceApi {

    public UtilResourceApi() {
        super();
    }

    public UtilResourceApi(ZAPIClient apiClient) {
        super(apiClient);
    }

    /**
     * Get All Projects Get List of Projects
     *
     * @param contentType (required)
     * @return GetAllProjectsResponse
     * @throws ApiException if fails to make API call
     */
    public GetAllProjectsResponse utilProjectListGet(String contentType) throws ApiException {
        return utilProjectListGetWithHttpInfo(contentType).getData();
    }

    /**
     * Get All Projects Get List of Projects
     *
     * @param contentType (required)
     * @return ApiResponse&lt;GetAllProjectsResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetAllProjectsResponse> utilProjectListGetWithHttpInfo(String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/util/project-list";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = { "application/json" };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<GetAllProjectsResponse> localVarReturnType = new GenericType<GetAllProjectsResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Get Components_Get Components Gets all component for a project
     *
     * @param contentType (required)
     * @return Object
     * @throws ApiException if fails to make API call
     */
    public Object utilTeststatusListGet(String contentType) throws ApiException {
        return utilTeststatusListGetWithHttpInfo(contentType).getData();
    }

    /**
     * Get Components_Get Components Gets all component for a project
     *
     * @param contentType (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Object> utilTeststatusListGetWithHttpInfo(String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/util/teststatus-list";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = { "application/json" };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<Object> localVarReturnType = new GenericType<Object>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Get All Versions Get List of Versions
     *
     * @param projectId   projectId of Util (required)
     * @param contentType (required)
     * @return GetAllVersionsResponse
     * @throws ApiException if fails to make API call
     */
    public GetAllVersionsResponse utilVersionBoardListGet(String projectId, String contentType) throws ApiException {
        return utilVersionBoardListGetWithHttpInfo(projectId, contentType).getData();
    }

    /**
     * Get All Versions Get List of Versions
     *
     * @param projectId   projectId of Util (required)
     * @param contentType (required)
     * @return ApiResponse&lt;GetAllVersionsResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetAllVersionsResponse> utilVersionBoardListGetWithHttpInfo(String projectId, String contentType)
            throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new ApiException(400,
                    "Missing the required parameter 'projectId' when calling utilVersionBoardListGet");
        }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/util/versionBoard-list";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "projectId", projectId));

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = { "application/json" };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<GetAllVersionsResponse> localVarReturnType = new GenericType<GetAllVersionsResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    /**
     * Get Version and porject information given projectId and versionId Get Version
     * and Project
     *
     * @param projectId   projectId of Util (required)
     * @param versionId   versionId of Util (required)
     * @param contentType (required)
     * @return GetAllVersionsResponse
     * @throws ApiException if fails to make API call
     */
    public GetAllVersionsResponse utilVersionBoardListGet(String projectId, String versionId, String contentType)
            throws ApiException {
        return utilVersionBoardListGetWithHttpInfo(projectId, versionId, contentType).getData();
    }

    /**
     * Get Version and porject information given projectId and versionId Get Version
     * and Project
     *
     * @param projectId   projectId of Util (required)
     * @param versionId   versionId of Util (required)
     * @param contentType (required)
     * @return ApiResponse&lt;GetAllVersionsResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<GetAllVersionsResponse> utilVersionBoardListGetWithHttpInfo(String projectId, String versionId,
            String contentType) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'projectId' is set
        if (projectId == null) {
            throw new ApiException(400,
                    "Missing the required parameter 'projectId' when calling utilVersionBoardListGet");
        }

        // verify the required parameter 'versionId' is set
        if (versionId == null) {
            throw new ApiException(400,
                    "Missing the required parameter 'versionId' when calling utilVersionBoardListGet");
        }

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // create path and map variables
        String localVarPath = "/zapi/latest/util/versionBoard-list";

        // query params
        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "projectId", projectId));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "versionId", versionId));

        if (contentType != null)
            localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = { "application/json" };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<GetAllVersionsResponse> localVarReturnType = new GenericType<GetAllVersionsResponse>() {
        };
        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

}
