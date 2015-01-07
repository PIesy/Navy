package com.mycompany.app;

import java.io.IOException;

public class GameFactory {

	public static GameProxy newGame(GameType type) throws IOException, IllegalArgumentException
	{
		switch(type){
		case Web:
			return new WebGameProxy();
		case Local:
			return new LocalGameProxy();
		default:
			throw new IllegalArgumentException("No valid type specified");
		}
	}
	
	public enum GameType {
		Local, Web, None
	}
}
