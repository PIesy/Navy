package com.mycompany.app;

import org.apache.http.entity.ContentType;

import com.mycompany.app.ContentMapperFactory.MapperType;

public class AppConfig
{
    public static final MapperType MAPPER_TYPE = MapperType.Json;
    public static final String CONTENT_TYPE_STRING  = "application/json";
    public static final ContentType CONTENT_TYPE = ContentType.APPLICATION_JSON;
    public static final String SCHEME = "http";
    public static final String HOST = "localhost";
    public static final int PORT = 8080;
}
