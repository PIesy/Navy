package com.mycompany.app;

import com.mycompany.data.game.GameRequest;
import com.mycompany.data.game.Directions;

public class GameRequestFactory 
{

	public static GameRequest getSetNameRequest(String name)
	{
		GameRequest result = new GameRequest();
		result.setType("setName");
		result.setName(name);
		return result;
	}
	
	public static GameRequest getSetShipRequest(int[] coordinates, Directions  direction)
	{
		GameRequest result = new GameRequest();
		result.setType("setShip");
		result.setDirection(direction);
		result.setCoordinates(coordinates);
		return result;
	}
	
	public static GameRequest getHitRequest(int[] coordinates)
	{
		GameRequest result = new GameRequest();
		result.setType("hit");
		result.setCoordinates(coordinates);
		return result;
	}
}
