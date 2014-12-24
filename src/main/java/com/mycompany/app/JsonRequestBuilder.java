package com.mycompany.app;

import javax.json.Json;
import javax.json.JsonObject;

import com.mycompany.app.JsonBuilder;

public class JsonRequestBuilder {

    public JsonRequestBuilder(int gameId)
    {
        this.gameId = gameId;
    }
    
    public JsonObject parseCoordinates(int[] coordinates)
    {
        JsonObject request = Json.createObjectBuilder()
                .add("type", "hit")
                .add("x", coordinates[0])
                .add("y", coordinates[1])
                .add("gameId", gameId)
                .build();
        return request;
    }

    public JsonObject parseShipCoordinates(int[] coordinates, Directions direction, String shipType)
    {
        JsonObject request = Json.createObjectBuilder().add("type", "setShip")
                .add("gameId", gameId)
                .add("type", "setShip")
                .add("x", coordinates[0])
                .add("y", coordinates[1])
                .add("shipType", shipType)
                .add("direction", direction.name())
                .build();
        return request;
    }
    
    public JsonObject parseName(String dataName, String name)
    {
         JsonObject object;
         
         object = Json.createObjectBuilder()
                 .add("gameId", gameId)
                 .add("type", "setName")
                 .add(dataName, name.toString())
                 .build();
         return object;
    }
    
    public JsonBuilder getBuilder()
    {
        return builder;
    }
    
    private final int gameId;
    private final JsonBuilder builder = new JsonBuilder();
}
