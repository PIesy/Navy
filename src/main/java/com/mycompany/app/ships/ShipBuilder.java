package com.mycompany.app.ships;

public class ShipBuilder {

	public static Ship BuildShip(ShipType type)
	{
		return new Ship(type.toString(), type.getSize());
	}
	
	public enum ShipType { 
		Boat(0), Schooner(1), Destroyer(2), Carrier(3);
		
		public int getSize()
		{
			return size;
		}
		
		private ShipType(int size)
		{
			this.size = size;
		}
		
		private final int size;
	}
}
