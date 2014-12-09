package com.mycompany.app;

import com.mycompany.app.GridItemState;
import com.mycompany.app.GridItemResponce;

public class GridItem {
	
	public GridItem(){}
	
	public GridItem(GridItemState state)
	{
		this.state = state;
	}
	
	public GridItemResponce Hit()
	{
		state = GridItemState.Hit;
		return GridItemResponce.getByOrdinal(state.ordinal());
	}
    
	public void setState(GridItemState state)
	{
		this.state = state;
	}
	
	public GridItemState getState()
	{
		return state;
	}
	
	private GridItemState state = GridItemState.Empty;
}
