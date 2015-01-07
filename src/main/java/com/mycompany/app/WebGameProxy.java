package com.mycompany.app;

import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.GameResponse;

import java.io.IOException;

import javax.json.JsonObject;

import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebGameProxy extends GameProxy
{

    public WebGameProxy() throws IOException
    {
        super(createGame());
    }

    @Override
    public GameResponse makeRequest(GameRequest request) throws IOException
    {
        addGameIdToRequest(request);
        JsonObject req = builder.getJsonObject(mapper.writeValueAsString(request));
        JsonObject response = httpHandler.makePostRequest("/Game", req, ContentType.APPLICATION_JSON);
        System.out.println(response.toString());
        return mapper.readValue(response.toString(), GameResponse.class);
    }

    private void addGameIdToRequest(GameRequest request)
    {
        request.setGameId(getRules().getGameId());
    }

    private static GameRules createGame() throws IOException
    {
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        JsonObject rules = httpHandler.makeGetRequest("/Game");
        System.out.println(rules.toString());
        return mapper.readValue(rules.toString(), GameRules.class);
    }

    private final JsonBuilder builder = new JsonBuilder();
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static HttpHandler httpHandler = new HttpHandler();
}
