package com.mycompany.app;

import java.io.IOException;

import javax.json.JsonObject;

import com.mycompany.app.Painter;
import com.mycompany.app.LocalPlayer;
import com.mycompany.app.GameRules;
import com.mycompany.app.GridDescriptor;
import com.mycompany.app.ships.Ship;

public class Game {

	public Game(GameRules rules, Painter painter, HttpHandler handler)
	{
		grid = new GridDescriptor(rules.fieldDimensions[0], rules.fieldDimensions[1]);
		gameHelper = new GameHelper(painter, rules, new ConsoleInputHandler());
		builder = new JsonRequestBuilder(rules.gameId);
        player = new LocalPlayer(rules);
		this.painter = painter;
		this.handler = handler;
		painter.drawGrid(grid);
	}
	
	public void start()
	{
		try {
			getPlayerName();
			prepareForGame();
			gameLoop();
		} catch (IOException e) {
			painter.printLine("Sorry not today");
		}
	}
	
	private void getPlayerName() throws IOException
	{
		JsonObject response;
		
		do {
    		response = gameHelper.getName(player, "Enter your name");
    		response = handler.makePostRequest("/Game", response);
    		gameHelper.printError(response);
		}
		while(response.containsKey("error"));
	}
	
	private void prepareForGame() throws IOException
	{
		JsonObject response = builder.getBuilder().getDummyObject();
		boolean end = false;
		Ship ship;
		
		while(!end)
		{
	        if((response.getString("state")) == "allSet"){
	            end = true;
	        }
			ship = player.getShip();
			do{
			    response = gameHelper.getShipPosition("Enter ship start coordinates in form: x y", "Enter sip direction(north, south, east, west)", ship.getType());
				response = handler.makePostRequest("/Game", response);
				gameHelper.printError(response);
				painter.drawGrid(grid.fill(response));
			}
			while(response.containsKey("error"));
		}
	}
	
	
	private JsonObject hit() throws IOException
	{
	    JsonObject response = builder.getBuilder().getDummyObject();
	    boolean end = false;
	    
	    while(!end)
	    {
            if((response.getString("state")) == "success"){
                end = true;
            }
	        response = gameHelper.getCoordinates("Enter coordinates to hit in form: x y");
	        response = handler.makePostRequest("/Game", response);
	        gameHelper.printError(response);
	    }
	    return response;
	}
	
	private void gameLoop() throws IOException
	{
	    JsonObject response = builder.getBuilder().getEmptyObject();
	    
	    while(!response.containsKey("gameEnd"))
	    {
	        response = hit();
	        grid.fill(response);
	        painter.drawGrid(grid);
	        gameHelper.printError(response);
	    }
	    painter.printLine(response.getString("gameEnd"));
	}

	private GameHelper gameHelper;
	private Painter painter;
	private LocalPlayer player;
	private GridDescriptor grid;
	private JsonRequestBuilder builder;
	private HttpHandler handler;
}
