package com.mycompany.app;

public class App
{
    public static void main(String[] args)
    {
        GameHandler game;
        try {
            game = new GameHandler(new ConsolePainter());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        game.start();
    }
}
