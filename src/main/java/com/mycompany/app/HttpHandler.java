package com.mycompany.app;

import java.io.IOException;
import java.net.Socket;

import javax.json.JsonObject;

import org.apache.http.*;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class HttpHandler {

    public HttpHandler()
    {
        initContext();
    }
    
    public JsonObject makeGetRequest(String url)
    {
        JsonObject response = null;
        BasicHttpRequest request = new BasicHttpRequest("GET", url);
        response = sendRequest(request);
        return response;
    }
    
    public JsonObject makePostRequest(String url, JsonObject data)
    {
        JsonObject response = null;
        HttpEntity requestData = new StringEntity(data.toString(), ContentType.APPLICATION_JSON);
        BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST", url);
        request.setEntity(requestData);
        response = sendRequest(request);
        return response;
    }
    
    private JsonObject sendRequest(HttpRequest request)
    {
        JsonObject responseData = null;
        try {
            if (!conn.isOpen()) {
                Socket socket = new Socket(host.getHostName(), host.getPort());
                conn.bind(socket);
            }
            httpexecutor.preProcess(request, httpproc, coreContext);
            HttpResponse response = httpexecutor.execute(request, conn, coreContext);
            httpexecutor.postProcess(response, httpproc, coreContext);
            responseData = (new JsonBuilder()).getJsonObject(EntityUtils.toString((response.getEntity())));
        } catch (Exception e) {} 
        finally {
            try {
                conn.close();
            } catch (IOException e) {}
        }
        return responseData;
    }
    
    private void initContext()
    {
        httpproc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("Test/1.1"))
                .add(new RequestExpectContinue()).build();
        coreContext.setTargetHost(host);
    }
    
    private HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
    private DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
    private HttpHost host = new HttpHost("localhost", 8080);
    private HttpProcessor httpproc;
    private final HttpCoreContext coreContext = HttpCoreContext.create();
}
