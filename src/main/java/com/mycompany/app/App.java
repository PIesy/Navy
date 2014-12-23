package com.mycompany.app;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HttpHandler handler = new HttpHandler();
        JsonObject obj = Json.createObjectBuilder().build();
		try {
			obj = handler.makeGetRequest("/Game");
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println(obj.toString());
        //Game game = new Game(12, 10, new ConsolePainter());
    }
}
