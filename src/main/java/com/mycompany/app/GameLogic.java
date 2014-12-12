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
		for(Player player: players)
		{
			getPlayerName(player);
			setShips(player);
		}
		try{
			while(true){
				turn();
				switchTurn();
			}
		} catch(GameOverException e){
			painter.printLine("Player " + players[currentPlayer].getName() + " is victorious!");
			painter.draw(players[currentPlayer].getField(), players[1 -currentPlayer].getField());
		}
	}
	
	public void setShips(Player player)
	{
		int[] coordinates;
		Directions direction;
		try {
			while(true)
			{
				painter.draw(player.getField(), false);
				coordinates = getCoordinates("Enter ship start position in form: x y");
				direction = getDirection("Enter your ship direction(North, East, South, West)");
				if(!tryToSetShip(player, coordinates, direction))
					continue;
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
			coordinates = getCoordinates("Enter node to hit");
			try {
				if(players[1 - currentPlayer].getField().hit(coordinates[0], coordinates[1]))
					end = false;
			} catch(AlreadyHitException e) {
				end = tryAgain();
			} catch (ShipIsKilledException e) {
				end = destroyShip();
			}
		} while (!end);
		painter.printLine("Missed!");
	}
	
	public void switchTurn()
	{
		currentPlayer = 1 - currentPlayer;
		painter.clearScreen();
		painter.printLine(players[currentPlayer].getName() + " turn");
	}
	
	private void getPlayerName(Player player)
	{
		painter.printLine("Enter your name please");
		player.setName(inputHandler.getLine());
	}
	
	private int[] getCoordinates(String message)
	{
		painter.printLine(message);
		return inputHandler.getCoordinates(ambits);
	}
	
	private Directions getDirection(String message)
	{
		painter.printLine(message);
		return inputHandler.getShipDirection();
	}
	
	private boolean tryToSetShip(Player player, int[] coordinates, Directions direction ) throws AllShipsSetException
	{
		try {
			player.getField().setShip(player.setShip(), coordinates[0], coordinates[1], direction);
		} catch(IndexOutOfBoundsException e) {
			painter.printLine(e.getMessage());
			return tryAgain();
		}
		return true;
	}
	
	private boolean tryAgain()
	{
		painter.printLine("Try again plz");
		return false;
	}
	
	private boolean destroyShip() throws GameOverException
	{
		painter.printLine("Target Down!");
		players[currentPlayer].destroyShip();
		return false;
	}
	
	private int ambits[];
	private InputHandler inputHandler = new InputHandler();
	private int currentPlayer = 0;
	private Player[] players;
	private Painter painter;
}
