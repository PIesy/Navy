package com.mycompany.app;

public enum GridItemResponce { 
	Hit, Missed, AlreadyHit, WrongOrdinal;
	
	public static GridItemResponce getByOrdinal(int ordinal)
	{
		switch(ordinal){
		case 0:
			return Hit;
		case 1:
			return Missed;
		case 2:
			return AlreadyHit;
		default:
			return WrongOrdinal;
		}
	}
}
