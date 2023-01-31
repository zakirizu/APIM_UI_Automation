package com.informatica.zapi.api;

import com.informatica.zapi.Configuration;
import com.informatica.zapi.ZAPIClient;

public abstract class AbstractResourceApi {
    protected ZAPIClient apiClient;

    public AbstractResourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AbstractResourceApi(ZAPIClient apiClient) {
        this.apiClient = apiClient;
    }


    public ZAPIClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ZAPIClient apiClient) {
        this.apiClient = apiClient;
    }

}