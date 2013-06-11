package com.github.gustav9797.CheeseMod;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Gravestone extends Block {
	
	public Gravestone(int i, int j)
    {
            super(i, j, Material.glass);
            this.setStepSound(soundStoneFootstep);
            this.setHardness(5F);
            this.setBlockName("Gravestone");
            setBlockBounds(0.0625F, 0F, 0.375F, 0.9375F, 1.618F, 0.625F);
    }
	
	@Override
	public boolean isOpaqueCube()
    {
            return false;
    }
	
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4) & 3;

        if (var5 != 3 && var5 != 1)
        {
            this.setBlockBounds(0.0625F, 0F, 0.375F, 0.9375F, 1.618F * (float)par1World.rand.nextInt(128)/128, 0.625F);
        }
        else
        {
            this.setBlockBounds(0.375F, 0F, 0.0625F, 0.625F, 1.618F * (float)par1World.rand.nextInt(128)/128, 0.9375F);
        }
        
        return false;
    }
	
	/*public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
            if(world.multiplayerWorld)
            {
                    return true;
            }
            ItemStack itemstack = entityplayer.inventory.getCurrentItem();
            if(itemstack == null)
            {
                    return true;
            }
            
            return false;
    }*/
	
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int var7 = par1World.getBlockMetadata(par2, par3, par4) >> 2;
        ++var6;
        var6 %= 4;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | var7 << 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | var7 << 2);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | var7 << 2);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | var7 << 2);
        }
    }
	
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3;

        if (var5 != 3 && var5 != 1)
        {
            this.setBlockBounds(0.0625F, 0F, 0.375F, 0.9375F, 1.618F, 0.625F);
        }
        else
        {
            this.setBlockBounds(0.375F, 0F, 0.0625F, 0.625F, 1.618F, 0.9375F);
        }
    }
	
}
