package com.mycompany.app;

import javax.json.JsonObject;

import com.mycompany.app.LocalPlayer;
import com.mycompany.app.ConsoleInputHandler;
import com.mycompany.app.JsonRequestBuilder;
import com.mycompany.app.GameRules;

public class GameHelper {

    public GameHelper(Painter painter, GameRules rules, ConsoleInputHandler inputHandler)
    {
        builder = new JsonRequestBuilder(rules.gameId);
        this.inputHandler = inputHandler;
        this.painter = painter;
        this.rules = rules;
    }
    
    public void printError(JsonObject data)
    {
        if(data.containsKey("error")){
            painter.printLine(data.getString("error"));
        }
    }
    
    public JsonObject getName(LocalPlayer player, String welcomeMessage)
    {
        painter.printLine(welcomeMessage);
        player.setName(inputHandler.getLine());
        return builder.parseName(player.getName());
    }
    
    public JsonObject getCoordinates(String welcomeMessage)
    {
        int[] coordinates = inputHandler.getCoordinates(rules.fieldDimensions);
        return builder.parseCoordinates(coordinates);
    }
    
    public JsonObject getShipPosition(String coordinatesMessage, String directionMessage, String shipType)
    {
        painter.printLine(coordinatesMessage);
        int[] coordinates = inputHandler.getCoordinates(rules.fieldDimensions);
        painter.printLine(directionMessage);
        Directions direction = inputHandler.getShipDirection();
        return builder.parseShipCoordinates(coordinates, direction, shipType);        
    }
    
    private final ConsoleInputHandler inputHandler;
    private final JsonRequestBuilder builder;
    private final GameRules rules;
    private final Painter painter;
}
