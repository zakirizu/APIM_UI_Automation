package com.informatica.zapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.informatica.zapi.ApiException;
import com.informatica.zapi.ApiResponse;
import com.informatica.zapi.GenericType;
import com.informatica.zapi.Pair;
import com.informatica.zapi.ZAPIClient;
import com.informatica.zapi.model.RootObject;
import com.informatica.zapi.model.SearchResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JiraTestCaseResourceApi extends AbstractResourceApi {

    public JiraTestCaseResourceApi() {
        super();
    }

    public JiraTestCaseResourceApi(ZAPIClient apiClient) {
        super(apiClient);
    }

    public String testPost(String contentType, RootObject body) throws ApiException {
        return testPostWithHttpInfo(contentType, body).getData();
    }

    public ApiResponse<String> testPostWithHttpInfo(String contentType, RootObject body) throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling cyclePost");
        }

        ObjectMapper Obj = new ObjectMapper();

        try {

            // get Oraganisation object as a json string
            String jsonStr = Obj.writeValueAsString(body);
            // Displaying JSON String
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // create path and map variables
        String localVarPath = "/api/latest/issue";

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

        GenericType<String> localVarReturnType = new GenericType<String>() {
        };
        return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    public String testPut(String contentType, RootObject body, String issuekey) throws ApiException {
        return testPutWithHttpInfo(contentType, body, issuekey).getData();
    }

    public ApiResponse<String> testPutWithHttpInfo(String contentType, RootObject body, String issuekey)
            throws ApiException {
        Object localVarPostBody = body;

        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            contentType = "application/json";
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling cyclePost");
        }

        ObjectMapper Obj = new ObjectMapper();

        try {

            // get Oraganisation object as a json string
            String jsonStr = Obj.writeValueAsString(body);
            // Displaying JSON String
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // create path and map variables
        String localVarPath = "/api/latest/issue/" + issuekey;

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

        GenericType<String> localVarReturnType = new GenericType<String>() {
        };
        return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

    public SearchResult searchJql(String jql) throws ApiException {
        return this.searchJql(jql, (Integer) null, (Integer) null, (List<String>) null);
    }

    public SearchResult searchJql(String jql, Integer maxResults, Integer startAt, List<String> fields)
            throws ApiException {
        List<String> expandosValues = Arrays.asList("schema", "names");
        String notNullJql = defaultString(jql);
        // return notNullJql.length() > 500 ? this.searchJqlImplPost(maxResults,
        // startAt, expandosValues,
        // notNullJql, fields) :
        return this.searchJqlImplGet(maxResults, startAt, expandosValues, notNullJql, fields).getData();
    }

    public String defaultString(String in) {
        return (Objects.nonNull(in) && in.length() > 0) ? in : "";

    }

    private ApiResponse<SearchResult> searchJqlImplGet(Integer maxResults, Integer startAt, List<String> expandosValues,
            String jql, List<String> fields) throws ApiException {

        Object localVarPostBody = null;
        String localVarPath = "/api/latest/search";

        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "jql", jql));
        if (null != fields && !fields.isEmpty())
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "fields", String.join(",", fields)));

        if (null != expandosValues && !expandosValues.isEmpty())
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "expand", String.join(",", expandosValues)));
        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = { "application/json" };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        GenericType<SearchResult> localVarReturnType = new GenericType<SearchResult>() {
        };

        if (null != maxResults)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "maxResults", maxResults));
        if (null != startAt)
            localVarQueryParams.addAll(apiClient.parameterToPairs("", "startAt", startAt));

        return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams,
                localVarFormParams, localVarAccept, localVarContentType, localVarReturnType);
    }

}
