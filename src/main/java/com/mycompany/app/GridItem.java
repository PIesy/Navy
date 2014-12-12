package com.mycompany.app;

import com.mycompany.app.exceptions.AlreadyHitException;
import com.mycompany.app.exceptions.ShipIsKilledException;
import com.mycompany.app.ships.Ship;

public class GridItem {
	
	public GridItem(){}
	
	public boolean hit() throws AlreadyHitException, ShipIsKilledException
	{
		if(hit){
			throw new AlreadyHitException();
		}
		hit = true;
		if(!empty)
		{
			ship.hit();
			return true;
		}
		return false;
	}
    
	public boolean isHit()
	{
		return hit;
	}
	
	public boolean isEmpty()
	{
		return empty;
	}
	
	public void setShip(Ship ship)
	{
		this.ship = ship;
		empty = false;
	}
	
	public void removeShip()
	{
		ship = null;
	}
	
	private Ship ship = null;
	private boolean empty = true;
	private boolean hit = false;
}
