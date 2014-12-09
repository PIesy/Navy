package com.mycompany.app;

public enum Directions {
	North(-1), East(1), South(1), West(-1);
	
	private Directions(int value) {
		this.value = value;
	}
	
	public int[] convertTo2DOffset(int offset, Directions direction)
	{
		int[] offset2D = {0,0};
		switch (direction) {
		case North:
			offset2D[1] += value * offset;
			break;
		case South:
			offset2D[1] += value * offset;
			break;
		case West:
			offset2D[0] += value * offset;
			break;
		case East:
			offset2D[0] += value * offset;
			break;
		}
		return offset2D;
	}
	
	private int value;
}
