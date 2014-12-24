package com.mycompany.app;

import java.io.IOException;

import javax.json.JsonObject;

import com.mycompany.app.Painter;
import com.mycompany.app.LocalPlayer;
import com.mycompany.app.GameRules;
import com.mycompany.app.GridDescriptor;
import com.mycompany.app.ships.Ship;

public class Game {

	public Game(GameRules rules, Painter painter)
	{
	    gameRules = rules;
		grid = new GridDescriptor(rules.fieldDimensions[0], rules.fieldDimensions[1]);
		this.painter = painter;
		builder = new JsonRequestBuilder(rules.gameId);
		player = new LocalPlayer(gameRules);
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
		JsonObject object;
		
		painter.printLine("Enter your name");
		player.setName(input.getLine());
		object = builder.parseName("name", player.getName());
		object = handler.makePostRequest("/Game", object);
		painter.printLine(object.toString());
	}
	
	private void prepareForGame() throws IOException
	{
		JsonObject response = builder.getBuilder().getEmptyObject();
		boolean end = false;
		
		while(!end)
		{
		    try {
		        if((response.getString("state")) == "allSet")
		            end = true;
		    } catch(NullPointerException e) {}
			Ship ship = player.getShip();
			do{
				response = handler.makePostRequest("/Game", getShipPosition(ship.getType()));
				painter.printLine(response.toString());
			}
			while(response.containsKey("error"));
		}
	}
	
	private JsonObject getShipPosition(String shipType)
	{
		JsonObject result = builder.getBuilder().getEmptyObject();
		int[] coordinates = input.getCoordinates(gameRules.fieldDimensions);
		Directions direction = input.getShipDirection();
		result = builder.parseShipCoordinates(coordinates, direction, shipType);
		
		return result;
	}
	
	private JsonObject hit() throws IOException
	{
	    int[] coordinates = input.getCoordinates(gameRules.fieldDimensions);
	    JsonObject response = builder.getBuilder().getEmptyObject();
	    while(!((response.getString("state")) == "success"))
	    {
	        coordinates = input.getCoordinates(gameRules.fieldDimensions);
	        response = handler.makePostRequest("/Game", builder.parseCoordinates(coordinates));
	        painter.printLine(response.toString());
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
	        painter.printLine(response.toString());
	    }
	    painter.printLine(response.getString("gameEnd"));
	}

	private GameRules gameRules;
	private Painter painter;
	private LocalPlayer player;
	private GridDescriptor grid;
	private ConsoleInputHandler input = new ConsoleInputHandler();
	private JsonRequestBuilder builder;
	private HttpHandler handler = new HttpHandler();
}
