package com.mycompany.app;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import com.mycompany.data.game.GameRequest;

public class XmlContentMapper implements ContentMapper
{

    @Override
    public String serializeGameRequest(GameRequest request) throws Exception
    {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(GameRequest.class);
        JAXBElement<GameRequest> element = new JAXBElement<>(new QName("gameRequest"), GameRequest.class, request);
        context.createMarshaller().marshal(element, writer);
        return writer.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserializeGameResponse(String response, Class<T> valueClass) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(valueClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T)unmarshaller.unmarshal(new StringReader(response));
    }

}
