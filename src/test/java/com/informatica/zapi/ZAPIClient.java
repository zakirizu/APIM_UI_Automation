package com.informatica.zapi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

public class ZAPIClient {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USERAGENT = "User-Agent";

    public static final String DOMAIN = "domain";
    public static final String PATH = "path";
    public static final String URI_CONTEXT_KEY = ZAPIClient.class.getName() + ".URI";
    private static Charset charset = StandardCharsets.UTF_8;

    private String basePath = "https:/infajira.informatica.com/rest";
    private String userName;
    private String password;
    protected int connectionTimeout = 0;
    protected int readTimeout = 0;

    protected String tempFolderPath = null;

    protected DateFormat dateFormat = new RFC3339DateFormat();

    final ObjectMapper mapper;

    public ZAPIClient() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        this.mapper.setDateFormat(new RFC3339DateFormat());

        // configure();
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZAPIClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public ZAPIClient setConnectTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Set the read timeout (in milliseconds). A value of 0 means no timeout,
     * otherwise values must be between 1 and {@link Integer#MAX_VALUE}.
     * 
     * @param readTimeout Read timeout in milliseconds
     * @return API client
     */
    public ZAPIClient setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    protected void buildPath(URIBuilder uriBuilder, String path, String method) {
        String temp = uriBuilder.getPath();
        if (temp == null) {
            temp = "";
        }
        if (!temp.endsWith("/")) {
            temp = temp + "/";
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        uriBuilder.setPath(temp + path);
    }

    protected void buildParam(URIBuilder uriBuilder, String name, Object... values) {
        if (values.length == 1) {
            Object v = values[0];
            if (v != null) {
                uriBuilder.setParameter(name, v.toString());
            }
        } else {
            Arrays.stream(values).filter(Objects::nonNull).forEach(o -> uriBuilder.addParameter(name, o.toString()));
        }
    }

    protected void buildHeader(RequestBuilder requestBuilder, String name, Object value, boolean replace) {
        if (replace) {
            requestBuilder.removeHeaders(name);
        }
        requestBuilder.addHeader(name, value == null ? null : value.toString());
    }

    // protected void buildCookie(com.intuit.karate.http.Cookie c) {
    // BasicClientCookie cookie = new BasicClientCookie(c.getName(), c.getValue());
    // for (Entry<String, String> entry : c.entrySet()) {
    // switch (entry.getKey()) {
    // case DOMAIN:
    // cookie.setDomain(entry.getValue());
    // break;
    // case PATH:
    // cookie.setPath(entry.getValue());
    // break;
    // }
    // }
    // if (cookie.getDomain() == null) {
    // cookie.setDomain(uriBuilder.getHost());
    // }
    // cookieStore.addCookie(cookie);
    // }

    // public void configure() {
    // clientBuilder = HttpClientBuilder.create();
    // clientBuilder.useSystemProperties();
    // cookieStore = new BasicCookieStore();
    // clientBuilder.setDefaultCookieStore(cookieStore);
    // SSLContext sslContext;
    // try {
    // SSLContextBuilder builder = SSLContexts.custom().setProtocol(null); // will
    // default to TLS if null
    // builder = builder.loadTrustMaterial(new TrustAllStrategy());
    // sslContext = builder.build();
    // SSLConnectionSocketFactory socketFactory = new
    // LenientSslConnectionSocketFactory(sslContext,
    // new NoopHostnameVerifier());
    // clientBuilder.setSSLSocketFactory(socketFactory);
    // } catch (Exception e) {
    // }
    // RequestConfig.Builder configBuilder =
    // RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000);
    // clientBuilder.setDefaultRequestConfig(configBuilder.build());
    // SocketConfig.Builder socketBuilder =
    // SocketConfig.custom().setSoTimeout(5000);
    // clientBuilder.setDefaultSocketConfig(socketBuilder.build());
    // }

    protected String getRequestUri(RequestBuilder requestBuilder) {
        return requestBuilder.getUri().toString();
    }

    public static String toString(InputStream is) {
        try {
            return toByteStream(is).toString(charset.name());
        } catch (Exception e) {
            return "";
        }
    }

    public static String toString(byte[] bytes) {
        return new String(bytes, charset);
    }

    public static byte[] toBytes(InputStream is) {
        return toByteStream(is).toByteArray();
    }

    private static ByteArrayOutputStream toByteStream(InputStream is) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            // throw new RuntimeException(e);
        }
        return result;
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public HttpEntity serialize(Object obj, Map<String, Object> formParams, String contentType) throws Exception {
        HttpEntity entity = null;
        if (contentType.startsWith("multipart/form-data")) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Entry<String, Object> param : formParams.entrySet()) {
                if (param.getValue() instanceof File) {
                    File file = (File) param.getValue();
                    FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
                    builder.addPart(param.getKey(), fileBody);
                } else {
                    StringBody stringBody = new StringBody(parameterToString(param.getValue()),
                            ContentType.MULTIPART_FORM_DATA);
                    builder.addPart(param.getKey(), stringBody);
                }
            }
            entity = builder.build();
        } else if (contentType.startsWith("application/x-www-form-urlencoded")) {
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (Entry<String, Object> param : formParams.entrySet()) {
                urlParameters.add(new BasicNameValuePair(param.getKey(), parameterToString(param.getValue())));
            }
            entity = new UrlEncodedFormEntity(urlParameters);
        } else {
            if (Objects.nonNull(obj)) {
                entity = new StringEntity(this.mapper.writeValueAsString(obj), charset);
            }
        }
        return entity;
    }

    public <T> T deserialize(HttpResponse response, GenericType<T> returnType) throws Exception {

        if (response == null || returnType == null) {
            return null;
        }

        if ("byte[]".equals(returnType.toString())) {
            // Handle binary response (byte array).

            byte[] bytes;
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null || responseEntity.getContent() == null) {
                bytes = new byte[0];
            } else {
                InputStream is = responseEntity.getContent();
                bytes = toBytes(is);
            }
            return (T) bytes;
        } else if (returnType.getRawType() == File.class) {
            // Handle file downloading.
            T file = (T) downloadFileFromResponse(response);
            return file;
        }
        String resJson = toString(response.getEntity().getContent());
        return (T) this.mapper.readValue(resJson, returnType.getRawType());
    }

    public File downloadFileFromResponse(HttpResponse response) throws ApiException {
        try {
            HttpEntity responseEntity = response.getEntity();
            InputStream is = responseEntity.getContent();
            File file = prepareDownloadFile(response);
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return file;
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    public File prepareDownloadFile(HttpResponse response) throws IOException {
        String filename = null;
        Header[] headers = response.getHeaders("Content-Disposition");
        if (Objects.nonNull(headers) && headers.length >= 1) {
            String contentDisposition = (String) headers[0].getValue();
            if (contentDisposition != null && !"".equals(contentDisposition)) {
                // Get filename from the Content-Disposition header.
                Pattern pattern = Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
                Matcher matcher = pattern.matcher(contentDisposition);
                if (matcher.find())
                    filename = matcher.group(1);
            }
        }

        String prefix;
        String suffix = null;
        if (filename == null) {
            prefix = "download-";
            suffix = "";
        } else {
            int pos = filename.lastIndexOf('.');
            if (pos == -1) {
                prefix = filename + "-";
            } else {
                prefix = filename.substring(0, pos) + "-";
                suffix = filename.substring(pos);
            }
            // File.createTempFile requires the prefix to be at least three
            // characters long
            if (prefix.length() < 3)
                prefix = "download-";
        }

        if (tempFolderPath == null)
            return File.createTempFile(prefix, suffix);
        else
            return File.createTempFile(prefix, suffix, new File(tempFolderPath));
    }

    protected Map<String, List<String>> buildResponseHeaders(HttpResponse response) {
        Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();

        Stream.of(response.getAllHeaders()).forEach(header -> {
            responseHeaders.put(header.getName(), Arrays.asList(header.getValue()));
        });

        return responseHeaders;
    }

    public <T> ApiResponse<T> invokeAPI(String path, String method, List<Pair> queryParams, Object body,
            Map<String, String> headerParams, Map<String, Object> formParams, String accept, String contentType,
            GenericType<T> returnType) {
        URIBuilder uriBuilder = null;
        URI uri = null;
        try {
            uriBuilder = new URIBuilder(this.basePath + path);
            
            if (queryParams != null) {
                for (Pair queryParam : queryParams) {
                    if (queryParam.getValue() != null) {
                        buildParam(uriBuilder, queryParam.getName(), queryParam.getValue());
                    }
                }
            }

            uri = uriBuilder.build();
        } catch (URISyntaxException e1) {
            throw new RuntimeException(e1);
        }

        

        RequestBuilder requestBuilder = RequestBuilder.create(method).setUri(uri);

        HttpEntity entity = null;
        try {
            entity = serialize(body, formParams, contentType);
        } catch (Exception e1) {

        }

        if (Objects.nonNull(entity)) {
            requestBuilder.setEntity(entity);
            requestBuilder.setHeader(CONTENT_TYPE, contentType);
        }
        String basicAuth = "Basic "
                + Base64.getEncoder().encodeToString((this.userName + ":" + this.password).getBytes());
        requestBuilder.setHeader(USERAGENT, "ZAPI-Automation-Client");
        requestBuilder.setHeader(ACCEPT, accept);
        requestBuilder.setHeader(AUTHORIZATION, basicAuth);

        for (Entry<String, String> entry : headerParams.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                requestBuilder.setHeader(entry.getKey(), value);
            }
        }

        HttpUriRequest httpRequest = requestBuilder.build();

        CloseableHttpClient client;
        if (this.connectionTimeout != 5000 && this.readTimeout != 5000) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(this.connectionTimeout)
                    .setConnectionRequestTimeout(this.connectionTimeout).setSocketTimeout(this.readTimeout).build();
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        } else {
            client = HttpClientBuilder.create().build();
        }
        
        CloseableHttpResponse httpResponse = null;

        try {
            
            httpResponse = client.execute(httpRequest);

            Map<String, List<String>> responseHeaders = buildResponseHeaders(httpResponse);
            ApiResponse<T> apiResponse = null;
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_NO_CONTENT) {
                apiResponse = new ApiResponse<>(statusCode, responseHeaders);
            } else if (200 <= statusCode && statusCode <= 299) {
                if (returnType == null)
                    apiResponse = new ApiResponse<>(statusCode, responseHeaders);
                else
                    apiResponse = new ApiResponse<>(statusCode, responseHeaders, deserialize(httpResponse, returnType));
            } else {
                String message = "error";
                String respBody = null;
                HttpEntity resEntity = httpResponse.getEntity();
                if (Objects.nonNull(resEntity)) {
                    try {
                        respBody = EntityUtils.toString(resEntity);
                        message = respBody;
                    } catch (RuntimeException e) {
                        // e.printStackTrace();
                    }
                }
                throw new ApiException(statusCode, message, buildResponseHeaders(httpResponse), respBody);
            }
            return apiResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally

        {
            try {
                if (Objects.nonNull(httpResponse)) {
                    httpResponse.close();
                }
                if (Objects.nonNull(client)) {
                    client.close();
                }
            } catch (Exception e) {
                // it's not critical, since the response object is local in
                // method invokeAPI; that's fine, just continue
            }
        }

    }

    /**
     * Format the given parameter object into string.
     * 
     * @param param Object
     * @return Object in string format
     */
    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return formatDate((Date) param);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection) param) {
                if (b.length() > 0) {
                    b.append(',');
                }
                b.append(String.valueOf(o));
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /*
     * Format to {@code Pair} objects.
     * 
     * @param collectionFormat Collection format
     * 
     * @param name Name
     * 
     * @param value Value
     * 
     * @return List of pairs
     */
    public List<Pair> parameterToPairs(String collectionFormat, String name, Object value) {
        List<Pair> params = new ArrayList<Pair>();

        // preconditions
        if (name == null || name.isEmpty() || value == null)
            return params;

        Collection valueCollection;
        if (value instanceof Collection) {
            valueCollection = (Collection) value;
        } else {
            params.add(new Pair(name, parameterToString(value)));
            return params;
        }

        if (valueCollection.isEmpty()) {
            return params;
        }

        // get the collection format (default: csv)
        String format = (collectionFormat == null || collectionFormat.isEmpty() ? "csv" : collectionFormat);

        // create the params based on the collection format
        if ("multi".equals(format)) {
            for (Object item : valueCollection) {
                params.add(new Pair(name, parameterToString(item)));
            }

            return params;
        }

        String delimiter = ",";

        if ("csv".equals(format)) {
            delimiter = ",";
        } else if ("ssv".equals(format)) {
            delimiter = " ";
        } else if ("tsv".equals(format)) {
            delimiter = "\t";
        } else if ("pipes".equals(format)) {
            delimiter = "|";
        }

        StringBuilder sb = new StringBuilder();
        for (Object item : valueCollection) {
            sb.append(delimiter);
            sb.append(parameterToString(item));
        }

        params.add(new Pair(name, sb.substring(1)));

        return params;
    }

    /**
     * Check if the given MIME is a JSON MIME. JSON MIME examples: application/json
     * application/json; charset=UTF8 APPLICATION/JSON application/vnd.company+json
     * "* / *" is also default to JSON
     * 
     * @param mime MIME
     * @return True if the MIME type is JSON
     */
    public boolean isJsonMime(String mime) {
        String jsonMime = "(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$";
        return mime != null && (mime.matches(jsonMime) || mime.equals("*/*"));
    }

    /**
     * Select the Accept header's value from the given accepts array: if JSON exists
     * in the given array, use it; otherwise use all of them (joining into a string)
     *
     * @param accepts The accepts array to select from
     * @return The Accept header to use. If the given array is empty, null will be
     *         returned (not to set the Accept header explicitly).
     */
    public String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        }
        for (String accept : accepts) {
            if (isJsonMime(accept)) {
                return accept;
            }
        }
        return StringUtil.join(accepts, ",");
    }

    /**
     * Select the Content-Type header's value from the given array: if JSON exists
     * in the given array, use it; otherwise use the first one of the array.
     *
     * @param contentTypes The Content-Type array to select from
     * @return The Content-Type header to use. If the given array is empty, JSON
     *         will be used.
     */
    public String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) {
            return "application/json";
        }
        for (String contentType : contentTypes) {
            if (isJsonMime(contentType)) {
                return contentType;
            }
        }
        return contentTypes[0];
    }

    /**
     * Escape the given string to be used as URL query value.
     * 
     * @param str String
     * @return Escaped string
     */
    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
}
