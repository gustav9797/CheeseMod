package com.github.gustav9797.CheeseMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InfectedCheeseBlock extends Block {
	public InfectedCheeseBlock(int id, int texture, Material material) {
		super(id, texture, material);
		blockID = id;
	}

	@Override
	public String getTextureFile() {
		return "/CheeseMod/terrain.png";
	}

	public static int blockID;
	
}
