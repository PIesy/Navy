package com.mycompany.app;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.data.game.GameRequest;

public class JsonContentMapper implements ContentMapper
{

    public JsonContentMapper()
    {
        mapper.setSerializationInclusion(Include.NON_EMPTY);
    }
    
    @Override
    public String serializeGameRequest(GameRequest request) throws JsonProcessingException
    {
        return mapper.writeValueAsString(request);
    }

    @Override
    public <T> T deserializeGameResponse(String response, Class<T> valueClass) throws Exception
    {
        return mapper.readValue(response, valueClass);
    }

    private final ObjectMapper mapper = new ObjectMapper();
}
