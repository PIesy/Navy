package com.mycompany.app;

import javax.json.JsonObject;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HttpHandler handler = new HttpHandler();
        JsonObject obj = handler.makeGetRequest("/Game");
        System.out.println(obj.toString());
        //Game game = new Game(12, 10, new ConsolePainter());
    }
}
