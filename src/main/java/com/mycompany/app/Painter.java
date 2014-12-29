package com.mycompany.app;

import com.mycompany.app.GridDescriptor;

public interface Painter {
	void printLine(String line);
	void clearScreen();
	void drawGrid(GridDescriptor grid, boolean enemy);
}
