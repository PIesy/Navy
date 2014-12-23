package com.mycompany.app;

import com.mycompany.app.Painter;
import com.mycompany.app.LocalPlayer;
import com.mycompany.app.GameRules;
import com.mycompany.app.GridDescriptor;

public class Game {

	public Game(GameRules rules, Painter painter)
	{
	    gameRules = rules;
		grid = new GridDescriptor(rules.fieldDimensions[0], rules.fieldDimensions[1]);
		this.painter = painter;
	}
	
	public void start()
	{
		painter.clearScreen();
	}

	private GameRules gameRules;
	private Painter painter;
	private LocalPlayer[] players;
	private GridDescriptor grid;
}
