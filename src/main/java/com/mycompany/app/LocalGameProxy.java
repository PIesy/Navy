package com.mycompany.app;

import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.GameResponse;

public class LocalGameProxy extends GameProxy
{

    public LocalGameProxy()
    {
        super(new GameRules());
    }

    @Override
    public GameResponse makeRequest(GameRequest request) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

}
