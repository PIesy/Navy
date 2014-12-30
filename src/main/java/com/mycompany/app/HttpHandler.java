package com.mycompany.app;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.json.JsonObject;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpHandler {

    public JsonObject makeGetRequest(String url) throws IOException
    {
        JsonObject response = null;
        URI uri = makeUri(url);
        HttpGet request = new HttpGet(uri);
        request.addHeader("Accept", "application/json");
        response = makeRequest(request);
        return response;
    }
    
    public JsonObject makePostRequest(String url, JsonObject data) throws IOException
    {
        JsonObject response = null;
        URI uri = makeUri(url);
        HttpEntity requestData = new StringEntity(data.toString(), ContentType.APPLICATION_JSON);
        HttpPost request = new HttpPost(uri);
        request.setEntity(requestData);
        response = makeRequest(request);
        return response;
    }

    private URI makeUri(String path)
    {
    	URI uri;
    	try {
			uri = new URIBuilder().setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(path)
					.build();
		} catch (URISyntaxException e) {
			return null;
		}
    	return uri;
    }
    
    private JsonObject makeRequest(HttpRequestBase request) throws IOException
    {
    	HttpResponse response;
    	JsonObject responseData;
    	
		response = httpClient.execute(request);
    	try {
			responseData = (new JsonBuilder()).getJsonObject(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) { 
			throw new IOException();
		}
    	return responseData;
    }
    
    private final HttpClient httpClient = HttpClients.createDefault();
}
