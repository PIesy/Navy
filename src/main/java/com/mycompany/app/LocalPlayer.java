package com.mycompany.app;

import java.util.LinkedList;

import javax.json.JsonObject;

import com.mycompany.app.ships.Ship;
import com.mycompany.app.ships.ShipBuilder;
import com.mycompany.app.ships.ShipBuilder.ShipType;

public class LocalPlayer 
{
    public LocalPlayer()
    {
        schoonersCount = 3;
        destroyersCount = 2;
        carriersCount = 1;
        boatsCount = 4;
    }
    
	public LocalPlayer(JsonObject data)
	{
	    carriersCount = data.getInt("carriersCount");
	    destroyersCount = data.getInt("destroyersCount");
	    schoonersCount = data.getInt("schoonersCount");
	    boatsCount = data.getInt("boatsCount");
		buildShips();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Ship getShip()
	{
		Ship ship = ships.pollLast();
		
		return ship;
	}
	
	private void buildShips()
	{
		buildBoats();
		buildSchooners();
		buildDestroyers();
		buildCarriers();
	}
	
	private void buildBoats()
	{
	    for(int i = 0; i < boatsCount; i++){
            ships.add(ShipBuilder.buildShip(ShipType.Boat));
        }
	}
	
	private void buildSchooners()
    {
        for(int i = 0; i < schoonersCount; i++){
            ships.add(ShipBuilder.buildShip(ShipType.Boat));
        }
    }
	
	private void buildDestroyers()
    {
        for(int i = 0; i < destroyersCount; i++){
            ships.add(ShipBuilder.buildShip(ShipType.Boat));
        }
    }
	
	private void buildCarriers()
    {
        for(int i = 0; i < carriersCount; i++){
            ships.add(ShipBuilder.buildShip(ShipType.Boat));
        }
    }

	private LinkedList<Ship> ships = new LinkedList<>();
	private final int carriersCount;
	private final int destroyersCount;
	private final int schoonersCount;
	private final int boatsCount;
	private String name = "";
}