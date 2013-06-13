package com.github.gustav9797.CheeseMod;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

public class LiquidCheeseStill extends BlockStationary {
	
	protected LiquidCheeseStill(int par1, Material material) {
        super(par1, material);
    
        this.blockHardness = 100.0F;
        this.setLightOpacity(2);
        this.disableStats();
        this.setRequiresSelfNotify();
                
    }
	
	@Override
	public String getTextureFile() 
	{
		return "/CheeseMod/terrain.png";
	}

}
