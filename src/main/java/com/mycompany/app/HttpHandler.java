package com.mycompany.app;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpHandler
{

    public String makeGetRequest(String url) throws IOException
    {
        URI uri = makeUri(url);
        HttpGet request = new HttpGet(uri);
        request.addHeader("Accept", AppConfig.CONTENT_TYPE_STRING);
        return makeRequest(request);
    }

    public String makePostRequest(String url, Object data) throws IOException
    {
        URI uri = makeUri(url);
        HttpEntity requestData = new StringEntity(data.toString(), AppConfig.CONTENT_TYPE);
        HttpPost request = new HttpPost(uri);
        request.setEntity(requestData);
        return makeRequest(request);
    }

    private URI makeUri(String path)
    {
        URI uri;
        try {
            uri = new URIBuilder().setScheme(AppConfig.SCHEME)
                    .setHost(AppConfig.HOST)
                    .setPort(AppConfig.PORT)
                    .setPath(path)
                    .build();
        } catch (URISyntaxException e) {
            return null;
        }
        return uri;
    }

    private String makeRequest(HttpRequestBase request) throws IOException
    {
        HttpResponse response;

        response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity());
    }

    private final HttpClient httpClient = HttpClients.createDefault();
}
