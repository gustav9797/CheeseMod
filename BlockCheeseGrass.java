package com.github.gustav9797.CheeseMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockCheeseGrass extends Block implements IPlantable
{
	int metadata = 0;
	public BlockCheeseGrass(int id)
	{
		super(id, Material.grass);
	}
	
	public String getTextureFile() 
	{
		return "/CheeseMod/terrain.png";
		this.blockIndexInTexture = metadata;
	}

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
}
