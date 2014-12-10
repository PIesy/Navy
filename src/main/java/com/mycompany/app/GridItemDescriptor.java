package com.mycompany.app;

import com.mycompany.app.GridItem;

enum ItemState {EmptyNonHit, EmptyHit, ShipNonHit, ShipHit}

public class GridItemDescriptor {

	public GridItemDescriptor(){}
	
	public GridItemDescriptor(GridItem item)
	{
		initialize(item);
	}
	
	public int toInt()
	{
		return state.ordinal();
	}
	
	public void initialize(GridItem item)
	{
		if (!item.IsHit() && item.IsEmpty())
			state = ItemState.EmptyNonHit;
		else if (item.IsHit() && item.IsEmpty())
			state = ItemState.EmptyHit;
		else if (!item.IsHit() && !item.IsEmpty())
			state = ItemState.ShipNonHit;
		else 
			state = ItemState.ShipHit;
	}
	
	private ItemState state = ItemState.EmptyNonHit;
}
