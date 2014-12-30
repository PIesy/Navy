package com.mycompany.app;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;

public class App 
{
    public static void main( String[] args )
    {
        HttpHandler handler = new HttpHandler();
        JsonObject obj = Json.createObjectBuilder().build();
		try {
			obj = handler.makeGetRequest("/Game");
		} catch (IOException e) {
			return;
		}
        Game game = new Game((new GameRules()).parseJson(obj), new ConsolePainter(), handler);
        game.start();
    }
}
