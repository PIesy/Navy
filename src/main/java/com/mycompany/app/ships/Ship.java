package com.mycompany.app.ships;

import com.mycompany.app.Directions;
import com.mycompany.app.Grid;
import com.mycompany.app.GridUndefinedException;

public class Ship {
	
	public Ship()
	{
		size = 0;
		shipType = "";
		boundGrid = null;
	}
	
	public Ship(String shipType, int size, Grid grid)
	{
		this.shipType = shipType;
		this.size = size;
		boundGrid = grid;
	}
	
	public String getType()
	{
		return shipType;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int[] getStartCoordinates()
	{
		return startCoordinates;
	}
	
	public int[] getEndCoordinates()
	{
		return endCoordinates;
	}
	
	public void setPosition(int x, int y, Directions direction) throws IndexOutOfBoundsException, GridUndefinedException
	{
		if(boundGrid == null)
			throw new GridUndefinedException("No grid set");
		
	}
	
	private final int size;
	private final String shipType;
	private final Grid boundGrid;
	private int[] startCoordinates = {0,0};
	private int[] endCoordinates = {0,0};
}
