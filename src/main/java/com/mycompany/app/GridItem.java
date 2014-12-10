package com.mycompany.app;

import com.mycompany.app.exceptions.AlreadyHitException;
import com.mycompany.app.exceptions.ShipIsKilledException;
import com.mycompany.app.ships.Ship;

public class GridItem {
	
	public GridItem(){}
	
	public void Hit() throws AlreadyHitException, ShipIsKilledException
	{
		if(isHit)
			throw new AlreadyHitException();
		if(!isEmpty)
			ship.Hit();
	}
    
	public boolean IsHit()
	{
		return isHit;
	}
	
	public boolean IsEmpty()
	{
		return isEmpty;
	}
	
	public void setShip(Ship ship)
	{
		this.ship = ship;
	}
	
	public void removeShip()
	{
		ship = null;
	}
	
	private Ship ship = null;
	private boolean isEmpty = true;
	private boolean isHit = false;
}
