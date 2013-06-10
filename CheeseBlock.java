package com.github.gustav9797.CheeseMod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class CheeseBlock extends Block {

	public CheeseBlock(int id, int texture, Material material) {
		super(id, texture, material);
		super.setTickRandomly(true);
	}

	@Override
	public String getTextureFile() {
		return "/CheeseMod/terrain.png";
	}

	public int tickRate() {
		return 2;
	}
	
	private void turnCheeseIntoInfected(World world, int amount, int x, int y, int z, int firstx, int firsty, int firstz)
	{
		try
		{
		if(world.getBlockId(x - 1, y, z) == super.blockID && firstx - x - 1 < amount) {
			world.setBlock(x-1, y, z, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x-1, y, z, firstx, firsty, firstz);
		}
		if(world.getBlockId(x + 1, y, z) == super.blockID && firstx - x + 1 > - amount) {
			world.setBlock(x + 1, y, z, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x + 1, y, z, firstx, firsty, firstz);
		}
		
		if(world.getBlockId(x, y - 1, z) == super.blockID && firsty - y - 1 < amount) {
			world.setBlock(x, y - 1, z, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x, y - 1, z, firstx, firsty, firstz);
		}
		if(world.getBlockId(x, y + 1, z) == super.blockID && firsty - y + 1 > - amount) {
			world.setBlock(x, y + 1, z, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x, y + 1, z, firstx, firsty, firstz);
		}
		
		if(world.getBlockId(x, y, z - 1) == super.blockID && firstz - z - 1 < amount) {
			world.setBlock(x, y, z - 1, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x, y, z - 1, firstx, firsty, firstz);
		}
		if(world.getBlockId(x, y, z + 1) == super.blockID && firstz - z + 1 > - amount) {
			world.setBlock(x, y, z + 1, super.blockID+1);
			turnCheeseIntoInfected(world, amount, x, y, z + 1, firstx, firsty, firstz);
		}
		}
		catch(Throwable e)
		{
			
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) < 32) {
			int side = random.nextInt(6);
			int xToSpread = 0;
			int yToSpread = 0;
			int zToSpread = 0;
			switch (side) {
			case 0:
				xToSpread -= 1;
				break;
			case 1:
				xToSpread += 1;
				break;
			case 2:
				yToSpread -= 1;
				break;
			case 3:
				yToSpread += 1;
				break;
			case 4:
				zToSpread -= 1;
				break;
			case 5:
				zToSpread += 1;
				break;
			}

			int xSpreadPos = x + xToSpread;
			int ySpreadPos = y + yToSpread;
			int zSpreadPos = z + zToSpread;

			 if (world.getBlockId(xSpreadPos, ySpreadPos, zSpreadPos) == mushroomBrown.blockID || world.getBlockId(xSpreadPos, ySpreadPos, zSpreadPos) == mushroomRed.blockID)
			 {
				 System.out.println("aha! mushroom!");
				 //Begin turning cheese into infected cheese..
				 turnCheeseIntoInfected(world, 50, xSpreadPos, ySpreadPos, zSpreadPos, xSpreadPos, ySpreadPos, zSpreadPos);
			 }
			try {
				if (world.getBlockId(xSpreadPos, ySpreadPos, zSpreadPos) != super.blockID
						&& world.setBlock(xSpreadPos, ySpreadPos, zSpreadPos,
								super.blockID)) {
					world.setBlockMetadata(xSpreadPos, ySpreadPos, zSpreadPos,
							world.getBlockMetadata(x, y, z) + 1);
				}
			} catch (Throwable e) {
			}
		}
	}

}
