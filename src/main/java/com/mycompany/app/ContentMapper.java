package com.mycompany.app;

import com.mycompany.data.game.GameRequest;

public interface ContentMapper
{
    public String serializeGameRequest(GameRequest request) throws Exception;
    public <T> T deserializeGameResponse(String response, Class<T> valueClass) throws Exception;
}
