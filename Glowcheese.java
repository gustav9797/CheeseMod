package com.github.gustav9797.CheeseMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Glowcheese extends Block {

	public Glowcheese(int id, int texture, Material material) 
	{
		super(id, texture, material);
	}
	
	@Override
	public String getTextureFile() 
	{
		return "/CheeseMod/terrain.png";
	}
}
