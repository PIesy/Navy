package com.mycompany.app;

import com.mycompany.app.ConsoleInputHandler;
import com.mycompany.app.GameFactory.GameType;

import com.mycompany.data.game.Directions;
import com.mycompany.data.game.GameResponse;

public class GameHelper
{

    public GameHelper(Painter painter, ConsoleInputHandler inputHandler)
    {
        this.inputHandler = inputHandler;
        this.painter = painter;
    }

    public boolean printIfError(GameResponse data)
    {
        if (data.getError() != null) {
            painter.printLine(data.getError());
            return true;
        }
        return false;
    }

    public GameType getGameType(String welcomeMessage)
    {
        painter.printLine(welcomeMessage);
        return inputHandler.getType();
    }

    public String getName(String welcomeMessage)
    {
        painter.printLine(welcomeMessage);
        return inputHandler.getLine();
    }

    public int[] getCoordinates(String welcomeMessage, int[] boundaries)
    {
        painter.printLine(welcomeMessage);
        return inputHandler.getCoordinates(boundaries);
    }

    public Directions getDirection(String welcomeMessage)
    {
        painter.printLine(welcomeMessage);
        return inputHandler.getShipDirection();
    }

    private final ConsoleInputHandler inputHandler;
    private final Painter painter;
}
