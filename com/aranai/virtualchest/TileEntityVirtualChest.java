/*
 * TileEntityVirtualChest is a simple class which overrides one method in TileEntityChest
 * The method is responsible for validating the selected chest against the world state.
 * For our purposes, the chest does not exist in the world, so we want to skip these checks.
 */
package com.aranai.virtualchest;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.TileEntityChest;

public class TileEntityVirtualChest extends TileEntityChest {
	TileEntityVirtualChest()
	{
		super();
	}
	
	@Override
	public boolean a_(EntityHuman entityhuman) {
		/*
		 * For this proof of concept, we ALWAYS validate the chest.
		 * This behavior has not been thoroughly tested, and may cause unexpected results depending on the state of the player.
		 * 
		 * Depending on your purposes, you might want to change this.
		 * It would likely be preferable to enforce your business logic outside of this file instead, however.
		 */
		return true;
	}
}
