package com.github.gustav9797.CheeseMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;

public class BlockCheeseGrass extends BlockFlower {
	public BlockCheeseGrass(int id)
	{
		super(id, 16);
		
	}
	
	@Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == CheeseBlock.blockID || par1 == InfectedCheeseBlock.blockID;
    }
	
	public String getTextureFile() 
	{
		return "/CheeseMod/terrain.png";
	}
}
