package com.mycompany.app;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class GridDescriptor {
    
    public GridDescriptor(int size_x, int size_y)
    {
        grid = new int[size_y][size_x];
        dimensions[0] = size_x;
        dimensions[1] = size_y;
    }
    
    public void fill(JsonObject data)
    {
        JsonArray fieldArray = data.getJsonArray("field");
        int i = 0, j = 0;
        
        for (JsonValue row : fieldArray) {
            for (JsonValue column : (JsonArray)row) {
                grid[i][j] = ((JsonNumber)column).intValue();
                j++;
            }
            i++;
        }
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
    private int[] dimensions = new int[2];
}
