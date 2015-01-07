package com.mycompany.app;

public class GridDescriptor {
    
    public GridDescriptor(int[] dimensions)
    {
        grid = new int[dimensions[1]][dimensions[0]];
        this.dimensions = dimensions;
    }
    
    public GridDescriptor fill(int[][] data)
    {
    	if(data != null){
    		grid = data;
    	}
        return this;
    }
    
    public int getGridValue(int x, int y)
    {
        return grid[y][x];
    }
    
    public int[] getDimensions()
    {
        return dimensions;
    }
    
    private int[][] grid;
    private final int[] dimensions;
}
