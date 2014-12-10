package com.mycompany.app;

import com.mycompany.app.Grid;
import com.mycompany.app.Painter;

class Player 
{
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	private String name = "";
}

public class Game {

	public Game(int fieldSizeX, int fieldSizeY, Painter painter)
	{
		for (int i = 0; i < 2; i++)
			fields[i] = new Grid(fieldSizeX, fieldSizeY);
		this.painter = painter;
	}
	
	public void Start()
	{
		painter.draw(fields[0], fields[1]);
	}

	private Painter painter;
	private Player[] players = new Player[2];
	private Grid fields[] = new Grid[2];
}
