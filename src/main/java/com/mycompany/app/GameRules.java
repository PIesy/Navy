package com.mycompany.app;

import javax.json.JsonObject;

public class GameRules implements Cloneable{

    public GameRules parseJson(JsonObject data)
    {
        fieldDimensions[0] = data.getJsonArray("fieldDimensions").getInt(0);
        fieldDimensions[1] = data.getJsonArray("fieldDimensions").getInt(1);
        carriersCount = data.getInt("carriersCount");
        destroyersCount = data.getInt("destroyersCount");
        schoonersCount = data.getInt("schoonersCount");
        boatscount = data.getInt("boatsCount");
        gameId = data.getInt("gameId");
        return this;
    }
    
    public int[] fieldDimensions = {12, 10};
    public int carriersCount = 1;
    public int destroyersCount = 2;
    public int schoonersCount = 3;
    public int boatscount = 4;
    public int gameId = -1;
}
