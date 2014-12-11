package com.mycompany.app;

import java.io.PrintWriter;

import com.mycompany.app.Painter;

public class ConsolePainter implements Painter{

	public void draw(Grid currentPlayerGrid, Grid enemyGrid) {
		drawGrid(false, currentPlayerGrid);
		writer.println("---------------------------------------------------------");
		drawGrid(true, enemyGrid);
	}

	private void drawGrid(boolean isEnemy, Grid grid)
	{
		GridItemDescriptor[][] field = grid.getState();
		
		for(int i = 0; i < grid.getSizeVertical(); i++)
		{
			for (int j = 0; j < grid.getSizeHorizontal(); j++)
				writer.print(getCharFromStateIdentifier(isEnemy, field[i][j].toInt()));
			writer.println();
		}
	}
	
	public void printLine(String line) {
		System.out.println(line);
	}
	
	private char getCharFromStateIdentifier(boolean enemy, int identifier)
	{
		switch(identifier){
		case 0:
			return '-';
		case 1:
			return '*';
		case 2:
			if(enemy)
				return '-';
			return 'S';
		case 3:
			return 'D';
		default:
			return '!';
		}
	}
	
	private PrintWriter writer = new PrintWriter(System.out, true);
}
