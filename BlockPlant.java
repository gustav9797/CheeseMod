package com.github.gustav9797.CheeseMod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockPlant extends Block {

	public BlockPlant(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		super.setTickRandomly(true);
	}
	
	public void updateTick(World world, int x, int y, int z, Random random) 
	{
		int[] growAngles = getGrowAngles();
		switch(growAngles[random.nextInt(growAngles.length)])
		{
		case 0:
			x++;
			break;
		case 1:
			x--;
			break;
		case 2:
			y++;
			break;
		case 3:
			y--;
			break;
		case 4:
			z++;
			break;
		case 5:
			z--;
			break;
		//default:
		//	return;
		}
		
		Grow(world, random, x, y, z);
	}
	
	protected void Grow(World world, Random random, int x, int y, int z)
	{
		int neighbors = CountNeighbors(world, x, y, z);
		int nearNeighbors = CountNearNeighbors(world, x, y, z);
		
		if (neighbors >= getMinNeighbors() && neighbors <= getMaxNeighbors() && neighbors >= getMinNearNeighbors() && neighbors <= getMaxNearNeighbors())
		{
			world.setBlockWithNotify(x, y, z, BlockToPlace(world, x, y, z));
		}
	}
	
	protected int CountNeighbors(World world, int x, int y, int z)
	{
		int neighbors = 0;
		for(int i = 0; i < 27; i++)
		{
			if (world.getBlockId(x+i%3, y+(i/3)%3, z+(i/9)%3) != 0)
				neighbors++;
		}
		return neighbors;
	}
	
	protected int CountNearNeighbors(World world, int x, int y, int z)
	{
		int neighBors = 0;
		int xx,yy,zz;
		for(int i = 0; i < 6; i++)
		{
			xx = x;
			yy = y;
			zz = z;
			
			switch(i)
			{
			case 0:
				xx++;
				break;
			case 1:
				xx--;
				break;
			case 2:
				yy++;
				break;
			case 3:
				yy--;
				break;
			case 4:
				zz++;
				break;
			case 5:
				zz--;
				break;
			}
			
			if (world.getBlockId(xx, yy, zz) != 0)
				neighBors++;
		}
		return neighBors;
	}
	
	protected int[] getGrowAngles()
	{
		return new int[]{0,1,2,3,4,5};
	}
	
	protected int BlockToPlace(World world, int x, int y, int z)
	{
		return this.blockID;
	}
	
	protected int getMinNeighbors()
	{
		return 1;
	}
	
	protected int getMaxNeighbors()
	{
		return 1;
	}
	
	protected int getMinNearNeighbors()
	{
		return 1;
	}
	
	protected int getMaxNearNeighbors()
	{
		return 1;
	}
}
