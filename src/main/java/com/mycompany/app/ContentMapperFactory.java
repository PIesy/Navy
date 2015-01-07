package com.mycompany.app;

public class ContentMapperFactory
{
    
    public static ContentMapper createMapper(MapperType type)
    {
        switch (type) {
        case Json:
            return new JsonContentMapper();
        case Xml:
            return new XmlContentMapper();
        default:
            throw new IllegalArgumentException("Invalid mapper type");
        }
    }
    
    public enum MapperType 
    {
        Json, Xml, None
    }
}
