package com.mycompany.app;

import com.mycompany.app.GridItem; 
import com.mycompany.app.exceptions.AlreadyHitException;
import com.mycompany.app.exceptions.ShipIsKilledException;
import com.mycompany.app.ships.Ship;

public class Grid {

	public Grid(){}
	
	public Grid(int sizeX, int sizeY){
		InitializeGrid(sizeX , sizeY);
	}
	
	public void InitializeGrid(int sizeX, int sizeY){
		dimensions[0] = sizeX;
		dimensions[1] = sizeY;
		grid = new GridItem[sizeY][sizeX];
		for (int i = 0; i < sizeY; i++)
			for (int j = 0; j < sizeX; j++)
				grid[i][j] = new GridItem();
	}
	
	public GridItemDescriptor[][] getState()
	{
		GridItemDescriptor[][] states = new GridItemDescriptor[dimensions[1]][dimensions[0]];
		for(int i = 0; i < dimensions[1]; i++)
			for (int j = 0; j < dimensions[0]; j++)
			{
				states[i][j] = new GridItemDescriptor();
				states[i][j].initialize(grid[i][j]);
			}
		return states;
	}

	public void Hit(int x, int y) throws AlreadyHitException, ShipIsKilledException
	{
		grid[y][x].Hit();
	}
	
	public int[] getSize()
	{
		return dimensions;
	}
	
	public int getSizeHorizontal()
	{
		return dimensions[0];
	}
	
	public int getSizeVertical()
	{
		return dimensions[1];
	}
	
	public void SetShip(Ship ship, int x, int y, Directions direction)
	{
		int[] offset = direction.convertTo2DOffset();
		int[] startCoordinates = {x, y};
		int[] endCoordinates = new int[2];
		
		for(int i = 0; i < 2; i++)
			endCoordinates[i] = startCoordinates[i] + offset[i] * ship.getSize();
		if((offset[0] < 0) || (offset[1] < 0))
			setShipInGrid(endCoordinates, startCoordinates, ship);
		else 
			setShipInGrid(startCoordinates, endCoordinates, ship);
	}
	
	public boolean IsOutOfBounds(int[] coordinates)
	{
		for(int i = 0; i < 2; i++)
			if((coordinates[i] < 0) || (coordinates[i] > dimensions[i] - 1))
				return true;
		return false;
	}
	
	private void setShipInGrid(int[] startCoordinates, int[] endCoordinates, Ship ship)
	{
		for(int i = startCoordinates[1]; i <= endCoordinates[1]; i++ )
			for(int j = startCoordinates[0]; j <= endCoordinates[0]; j++)
				grid[i][j].setShip(ship);
	}
	
	private GridItem[][] grid;
	private int dimensions[] = new int[2];
}
