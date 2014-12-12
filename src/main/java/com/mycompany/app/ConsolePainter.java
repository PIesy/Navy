package com.mycompany.app;

import java.io.PrintWriter;
import com.mycompany.app.Painter;

public class ConsolePainter implements Painter{

	public void draw(Grid currentPlayerGrid, Grid enemyGrid) {
		drawGrid(false, currentPlayerGrid);
		writer.println("====================================================");
		drawGrid(true, enemyGrid);
	}
	
	public void draw(Grid grid, boolean isEnemy) {
		drawGrid(isEnemy, grid);
	}

	public void printLine(String line) {
		writer.println(line);
	}
	
	public void clearScreen() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 25; i++){
			str.append("\n\n");
		}
		printLine(str.toString());
	}
	
	private void drawGrid(boolean isEnemy, Grid grid)
	{
		GridItemDescriptor[][] field = grid.getState();
		
		drawGridHeader(grid);
		for(int i = 0; i < grid.getSizeVertical(); i++)
		{
			writer.print(i + "||");
			for (int j = 0; j < grid.getSizeHorizontal(); j++){
				writer.print(getStringFromStateIdentifier(isEnemy, field[i][j].toInt()));
			}
			writer.print("||");
			writer.println();
		}
	}
	
	private void drawGridHeader(Grid grid)
	{
		writer.print("   ");
		for (int j = 0; j < grid.getSizeHorizontal(); j++){
			writer.print(" " + (j + 1) + " ");
		}
		writer.println();
		writer.print("___");
		for (int j = 0; j < grid.getSizeHorizontal(); j++){
			writer.print("___");
		}
		writer.print("__");
		writer.println();
	}
	
	private String getStringFromStateIdentifier(boolean enemy, int identifier)
	{
		switch(identifier){
		case 0:
			return " - ";
		case 1:
			return " * ";
		case 2:
			if(enemy)
				return " - ";
			return " S ";
		case 3:
			return " X ";
		default:
			return " ! ";
		}
	}
	
	private PrintWriter writer = new PrintWriter(System.out, true);
}
