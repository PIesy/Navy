package com.mycompany.app;

import org.apache.http.entity.ContentType;

import com.mycompany.data.ContentMapperFactory.MapperType;


public class AppConfig
{
    public static final MapperType MAPPER_TYPE = MapperType.Xml;
    public static final String CONTENT_TYPE_STRING  = "application/xml";
    public static final ContentType CONTENT_TYPE = ContentType.APPLICATION_XML;
    public static final String SCHEME = "http";
    public static final String HOST = "localhost";
    public static final int PORT = 8080;
}
