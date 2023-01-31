package com.informatica.zapi;

public class Configuration {
    private static ZAPIClient defaultApiClient = new ZAPIClient();

    /**
     * Get the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @return Default API client
     */
    public static ZAPIClient getDefaultApiClient() {
        return defaultApiClient;
    }

    /**
     * Set the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @param apiClient API client
     */
    public static void setDefaultApiClient(ZAPIClient apiClient) {
        defaultApiClient = apiClient;
    }
}