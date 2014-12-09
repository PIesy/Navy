package com.mycompany.app;

import com.mycompany.app.GridItem; 

public class Grid {

	public Grid(){}
	
	public Grid(int sizeX, int sizeY){
		InitializeGrid(sizeX , sizeY);
	}
	
	public void InitializeGrid(int sizeX, int sizeY){
		dimensions[1] = sizeX;
		dimensions[2] = sizeY;
		grid = new GridItem[sizeY][sizeX];
	}
	
	public GridItemState[][] getState()
	{
		GridItemState[][] states = new GridItemState[dimensions[0]][dimensions[1]];
		for(int i = 0; i < dimensions[1]; i++)
			for (int j = 0; j < dimensions[0]; j++)
				states[i][j] = grid[i][j].getState();
		return states;
	}

	public GridItemResponce Hit(int x, int y)
	{
		return grid[y][x].Hit();
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
	
	public boolean isOutOfBounds(int[] coordinates)
	{
		for(int i = 0; i < 2; i++)
			if((coordinates[i] < 0) || (coordinates[i] > dimensions[i]))
				return true;
		return false;
	}
	
	private GridItem[][] grid;
	private int dimensions[] = new int[2];
}
