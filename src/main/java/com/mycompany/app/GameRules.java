package com.mycompany.app;

import javax.json.JsonObject;

public class GameRules implements Cloneable{

    public void parseJson(JsonObject data)
    {
        fieldDimensions[0] = data.getInt("size_x");
        fieldDimensions[1] = data.getInt("size_y");
        carriersCount = data.getInt("carriersCount");
        destroyersCount = data.getInt("destroyersCount");
        schoonersCount = data.getInt("schoonersCount");
        boatscount = data.getInt("boatsCount");
    }
    
    public int[] fieldDimensions = {12, 10};
    public int carriersCount = 1;
    public int destroyersCount = 2;
    public int schoonersCount = 3;
    public int boatscount = 4;
}
