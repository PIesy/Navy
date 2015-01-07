package com.mycompany.app;

import com.mycompany.data.game.Game;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.GameResponse;

public class LocalGameProxy extends GameProxy
{

    public LocalGameProxy()
    {
        super(new GameRules());
        game = new Game(getRules());
    }

    @Override
    public GameResponse makeRequest(GameRequest request) throws Exception
    {
        switch (request.getType()) {
        case "setName":
            return game.setPlayerName(request.getName());
        case "setShip":
            return game.setShip(request.getCoordinates(), request.getDirection());
        case "hit":
            return game.hit(request.getCoordinates());
        default:
            throw new IllegalArgumentException("Not valid request type");
        }
    }

    private final Game game; 
}
