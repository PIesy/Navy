package com.mycompany.app;

import com.mycompany.app.Grid;

public interface Painter {
	void draw(Grid currentPlayerGrid, Grid enemyGrid);
	void printLine(String line);
}
