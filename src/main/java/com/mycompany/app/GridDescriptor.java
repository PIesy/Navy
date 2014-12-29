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
    
    public GridDescriptor fill(JsonObject data, String fieldName)
    {
        if(!data.containsKey(fieldName))
            return this;
        JsonArray fieldArray = data.getJsonArray(fieldName);
        int i = 0, j = 0;
        
        for (JsonValue row : fieldArray) {
            for (JsonValue column : (JsonArray)row) {
                grid[i][j] = ((JsonNumber)column).intValue();
                j++;
            }
            j = 0;
            i++;
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
    private int[] dimensions = new int[2];
}
