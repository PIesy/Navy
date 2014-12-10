package com.mycompany.app;

import com.mycompany.app.Painter;

public class ConsolePainter implements Painter{

	public void draw(Grid currentPlayerGrid, Grid enemyGrid) {
		drawCurrentPlayerGrid(currentPlayerGrid);
		System.out.println("---------------------------------------------------------");
		drawEnemyPlayerGrid(enemyGrid);
	}

	private void drawCurrentPlayerGrid(Grid grid)
	{
		GridItemDescriptor[][] field = grid.getState();
		
		for(int i = 0; i < grid.getSizeVertical(); i++)
		{
			for (int j = 0; j < grid.getSizeHorizontal(); j++)
				System.out.print(getCharFromStateIdentifier(false, field[i][j].toInt()));
			System.out.print('\n');
		}
	}
	
	private void drawEnemyPlayerGrid(Grid grid)
	{
		GridItemDescriptor[][] field = grid.getState();
		
		for(int i = 0; i < grid.getSizeVertical(); i++)
		{
			for (int j = 0; j < grid.getSizeHorizontal(); j++)
				System.out.print(getCharFromStateIdentifier(true, field[i][j].toInt()));
			System.out.print('\n');
		}
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
}
