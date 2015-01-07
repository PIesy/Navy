package com.mycompany.app;

import com.mycompany.data.ContentMapper;
import com.mycompany.data.ContentMapperFactory;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.GameResponse;

public class WebGameProxy extends GameProxy
{

    public WebGameProxy() throws Exception
    {
        super(createGame());
    }

    @Override
    public GameResponse makeRequest(GameRequest request) throws Exception
    {
        addGameIdToRequest(request);
        String response = httpHandler.makePostRequest("/Game", mapper.serialize(request, GameRequest.class));
        System.out.println(response);
        return mapper.deserialize(response, GameResponse.class);
    }

    private void addGameIdToRequest(GameRequest request)
    {
        request.setGameId(getRules().getGameId());
    }

    private static GameRules createGame() throws Exception
    {
        String rules = httpHandler.makeGetRequest("/Game");
        System.out.println(rules);
        return mapper.deserialize(rules, GameRules.class);
    }

    private final static ContentMapper mapper = ContentMapperFactory.getMapper(AppConfig.MAPPER_TYPE);
    private final static HttpHandler httpHandler = new HttpHandler();
}
