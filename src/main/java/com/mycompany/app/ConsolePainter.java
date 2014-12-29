package com.mycompany.app;

import java.io.PrintWriter;
import com.mycompany.app.Painter;

public class ConsolePainter implements Painter
{
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
	
    public void drawGrid(GridDescriptor grid, boolean enemy) 
    {
        int[] dimensions = grid.getDimensions();
        
        for(int i = 0; i < dimensions[1]; i++){
            for(int j = 0; j < dimensions[0]; j++){
                writer.print(getStringFromStateIdentifier(enemy, grid.getGridValue(j, i)));
            }
            writer.println();
        }
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
