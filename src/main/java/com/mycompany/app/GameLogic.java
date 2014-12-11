package com.mycompany.app;

import com.mycompany.app.Player;
import com.mycompany.app.exceptions.AllShipsSetException;
import com.mycompany.app.exceptions.AlreadyHitException;
import com.mycompany.app.exceptions.GameOverException;
import com.mycompany.app.exceptions.ShipIsKilledException;
import com.mycompany.app.InputHandler;

public class GameLogic {

	public GameLogic(Player[] players, Painter painter)
	{
		this.players = players;
		this.painter = painter;
		ambits = new int[]{players[0].getField().getSizeHorizontal(), players[0].getField().getSizeVertical()};
	}
	
	public void start()
	{
		setShips(players[0]);
		setShips(players[1]);
		try{
			while(true){
				turn();
				switchTurn();
			}
		} catch(GameOverException e){}
	}
	
	public void setShips(Player player)
	{
		int[] coordinates;
		Directions direction;
		int i = 0;
		try{
			while(i < 1)
			{
				painter.printLine("Enter your ship start position plz");
				coordinates = inputHandler.getCoordinates(ambits);
				painter.printLine("Enter your ship direction(North, East, South, West)");
				direction = inputHandler.getShipDirection();
				try {
					player.getField().setShip(player.setShip(), coordinates[0], coordinates[1], direction);
				} catch(IndexOutOfBoundsException e) {
					continue;
				}
				i++;
				painter.printLine("Ship set");
			}
		}
		catch(AllShipsSetException e){}
	}
	
	public void turn() throws GameOverException
	{
		boolean end = false;
		int[] coordinates;
		do {
			painter.draw(players[currentPlayer].getField(), players[1 -currentPlayer].getField());
			end = true;
			painter.printLine("Enter node to hit");
			coordinates = inputHandler.getCoordinates(ambits);
			try {
				if(players[1 - currentPlayer].getField().hit(coordinates[0], coordinates[1]))
					end = false;
			} catch(AlreadyHitException e) {
				end = false;
				painter.printLine("Try again plz");
			} catch (ShipIsKilledException e) {
				end = false;
				painter.printLine("Target Down!");
				players[currentPlayer].destroyShip();
			}
		} while (!end);
	}
	
	public void switchTurn()
	{
		painter.printLine("Turn Switched");
		currentPlayer = 1 - currentPlayer;
	}
	
	private int ambits[];
	private InputHandler inputHandler = new InputHandler();
	private int currentPlayer = 0;
	private Player[] players;
	private Painter painter;
}
