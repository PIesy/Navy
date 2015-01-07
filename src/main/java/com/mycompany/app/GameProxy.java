package com.mycompany.app;

import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.GameResponse;

public abstract class GameProxy
{

    public GameProxy(GameRules rules)
    {
        this.rules = rules;
    }

    public GameRules getRules()
    {
        return rules;
    }

    public abstract GameResponse makeRequest(GameRequest request) throws Exception;

    private final GameRules rules;
}
