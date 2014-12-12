package com.mycompany.app;

import com.mycompany.app.Game;
import com.mycompany.app.ConsolePainter;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Game game = new Game(12, 10, new ConsolePainter());
        game.start();
    }
}
