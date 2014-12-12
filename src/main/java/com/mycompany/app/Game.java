package com.mycompany.app;

import com.mycompany.app.Grid;
import com.mycompany.app.Painter;
import com.mycompany.app.Player;

public class Game {

	public Game(int fieldSizeX, int fieldSizeY, Painter painter)
	{
		for (int i = 0; i < 2; i++)
			fields[i] = new Grid(fieldSizeX, fieldSizeY);
		for (int i = 0; i < 2; i++)
			players[i] = new Player(fields[i]);
		this.painter = painter;
	}
	
	public void start()
	{
		painter.clearScreen();
		logic = new GameLogic(players, painter);
		logic.start();
	}

	private GameLogic logic;
	private Painter painter;
	private Player[] players = new Player[2];
	private Grid fields[] = new Grid[2];
}
