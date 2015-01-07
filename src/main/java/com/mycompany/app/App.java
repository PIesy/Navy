package com.mycompany.app;

import java.io.IOException;

public class App
{
    public static void main(String[] args)
    {
        GameHandler game;
        try {
            game = new GameHandler(new ConsolePainter());
        } catch (IOException e) {
            return;
        }
        game.start();
    }
}
