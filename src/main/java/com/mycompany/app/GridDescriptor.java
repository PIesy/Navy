package com.mycompany.app;

import javax.json.JsonObject;

public class GridDescriptor {
    
    public GridDescriptor(int size_x, int size_y)
    {
        grid = new int[size_y][size_x];
    }
    
    public void fill(JsonObject data)
    {
        
    }
    
    private int[][] grid;
}
