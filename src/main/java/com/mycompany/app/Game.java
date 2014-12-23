package com.mycompany.app;

import java.io.IOException;

import javax.json.Json;
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
	}
	
	public void start()
	{
		try {
			getPlayerName();
			prepareForGame();
		} catch (IOException e) {
			painter.printLine("Sorry not today");
		}
	}
	
	private void getPlayerName() throws IOException
	{
		JsonObject object;
		
		painter.printLine("Enter your name");
		player.setName(input.getLine());
		object = builder.makeRequestObject("setName", "name", player.getName());
		object = handler.makePostRequest("/Game", object);
	}
	
	private void prepareForGame() throws IOException
	{
		JsonObject response = builder.getEmptyObject();
		while(!((response.getString("state")) == "allSet"))
		{
			Ship ship = player.getShip();
			do{
				response = handler.makePostRequest("/Game", getShipPosition(ship.getType()));
			}
			while(response.containsKey("error"));
		}
	}
	
	private JsonObject getShipPosition(String shipType)
	{
		JsonObject result = builder.getEmptyObject();
		int[] coordinates = input.getCoordinates(gameRules.fieldDimensions);
		Directions direction = input.getShipDirection();
		result = Json.createObjectBuilder().add("type", "setShip")
				.add("x", coordinates[0])
				.add("y", coordinates[1])
				.add("shipType", shipType)
				.add("direction", direction.name())
				.build();
		
		return result;
	}

	private GameRules gameRules;
	private Painter painter;
	private LocalPlayer player = new LocalPlayer();
	private GridDescriptor grid;
	private ConsoleInputHandler input = new ConsoleInputHandler();
	private JsonBuilder builder = new JsonBuilder();
	private HttpHandler handler = new HttpHandler();
}
