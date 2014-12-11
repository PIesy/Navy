package com.mycompany.app;

import com.mycompany.app.Grid;
import com.mycompany.app.Painter;
import com.mycompany.app.Player;
import com.mycompany.app.ships.ShipBuilder;
import com.mycompany.app.ships.ShipBuilder.ShipType;

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
		logic = new GameLogic(players, painter);
		logic.start();
		fields[0].setShip(ShipBuilder.buildShip(ShipType.Carrier), 5, 5, Directions.West);
		painter.draw(fields[0], fields[1]);
	}

	private GameLogic logic;
	private Painter painter;
	private Player[] players = new Player[2];
	private Grid fields[] = new Grid[2];
}
