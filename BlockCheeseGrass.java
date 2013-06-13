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
	public BlockCheeseGrass(int id)
	{
		super(id, Material.grass);
		this.blockIndexInTexture = 16;
		this.setTickRandomly(true);
        float var4 = 0.2F;
        this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}
	
	public int blockID()
	{
		return(this.blockID);
	}
	
	@Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	if(world.getBlockId(x, y, z) != 0 && world.getBlockId(x, y, z) != this.blockID)
    		return true;
    	return false;
    }
	
	@Override
	public void onSetBlockIDWithMetaData(World world, int par2, int par3, int par4, int par5)
	{
		this.blockIndexInTexture = 16 + world.getBlockMetadata(par2, par3, par4);
	}
	
	public String getTextureFile() 
	{
		return "/CheeseMod/terrain.png";
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
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
    
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }
}
