package com.github.gustav9797.CheeseMod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class OstWorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		for (int iii = 0; iii < 10; iii++)
		{
			for (int y = 48; y < 128; y++)
			{
				int x = chunkX*16 + random.nextInt(16);
				int z = chunkZ*16 + random.nextInt(16);
				
				/*for (int i = 0; i < random.nextInt(4); i++)
				{
					int xx = 0;
					int yy = 0;
					int zz = 0;
					for (int ii = 0; ii < random.nextInt(3); ii++)
					{
						switch(random.nextInt(6))
						{
						case 0:
							xx++;
							break;
						case 1:
							yy++;
							break;
						case 2:
							zz++;
							break;
						case 3:
							xx--;
							break;
						case 4:
							yy--;
							break;
						case 5:
							zz--;
							break;
						}*/
						
						
						//if (yy > 0 && yy < 255)
						if (world.getBlockId(x, y, z) == 3 && random.nextInt(250) == 0)
							world.setBlockWithNotify(x, y, z, CheeseMod.cheese.blockID);
						
						
					//}
				}
			}
		
		if (random.nextInt(64) == 0)
		{
			int x = chunkX*16+random.nextInt(16);
			int z = chunkZ*16+random.nextInt(16);
			int angle = random.nextInt(4);
			
			int xx = 0;
			int zz = 0;
			
			int width = 8 + random.nextInt(6)*4;
			int height = width + 4 + random.nextInt(4)*4;
			
			switch (angle%2)
			{
				case 1:
					xx = width;
					zz = height;
				case 0:
					xx = height;
					zz = width;
			}
			
			int minheight = 256;
			int maxheight = 0;
			
			for (int xxx = -(xx-1)/2; xxx <= (xx-1)/2 && minheight > 0; xxx++)
			{
				for (int zzz = -(zz-1)/2; zzz <= (zz-1)/2 && minheight > 0; zzz++)
				{
					for (int y = 255; y > 32; y--)
					{
						int block = world.getBlockId(x+xxx, y-1, z+zzz);
						
						if (block == 2 || block == 12 || block == 13)
						{
							if (y > maxheight)
								maxheight = y;
							
							if (y < minheight)
								minheight = y;
							
							break;
						}
						else if (block == 8 || block == 9)
						{
							minheight = 0;
							break;
						}
					}
				}
			}
			
			if (maxheight - minheight < 4)
			{
				for (int xxx = -(xx-1)/2; xxx <= (xx-1)/2; xxx++)
				{
					for (int zzz = -(zz-1)/2; zzz <= (zz-1)/2; zzz++)
					{
						for (int y = 255; y > 32; y--)
						{
							int block = world.getBlockId(x+xxx, y-1, z+zzz);
							if (block == 2 || block == 12)
							{
								if (Math.abs(xxx) == (xx-1)/2 || Math.abs(zzz) == (zz-1)/2) // walls and door
								{
									if (angle%2 == 0 && xxx == 0 || angle%2 == 1 && zzz == 0)
									{
										if (angle == 0 && zzz == -(zz-1)/2 ||
											angle == 1 && xxx == -(xx-1)/2 ||
											angle == 2 && zzz == (zz-1)/2 ||
											angle == 3 && xxx == (xx-1)/2)
										{
											//world.setBlockAndMetadataWithNotify(x + xxx, y, z + zzz, 0//107, angle); //door
											break;
										}
									}
									world.setBlockAndMetadataWithNotify(x + xxx, y , z + zzz, 139, random.nextInt(3) != 3 ? 1:0); //walls
								}
								else if (Math.abs(xxx)%(4-2*angle%2) == 1 && Math.abs(zzz)%(2+2*angle%2) == 1 && random.nextInt(2) == 0) // gravelstones
								{
									world.setBlockAndMetadata(x + xxx, y, z + zzz, CheeseMod.gravestone.blockID/**/, angle);//gravelstone
									world.setBlockAndMetadata(x + xxx, y-2, z + zzz, 52, 54); //zombie spawner
									TileEntityMobSpawner var19 = (TileEntityMobSpawner)world.getBlockTileEntity(x + xxx, y-2, z + zzz);
									var19.setMobID("Grave Zombie");
								}
								if (angle%2 == 0 && xxx == 0 || angle%2 == 1 && zzz == 0) // cobblestone path
								{
									world.setBlockAndMetadata(x + xxx, y-1 , z + zzz, 48, random.nextInt(3) == 0 ? 1:0);
									
									//torches
									/*if (Math.abs(xxx)*(angle%2)+1-angle%2 == 1 && Math.abs(zzz)*(1-angle%2)+angle%2 == 1)
									{
										world.setBlockWithNotify(x + xxx + 1 - angle%2, y , z + zzz + angle%2, 85);
										world.setBlockWithNotify(x + xxx - 1 + angle%2, y , z + zzz - angle%2, 85);
										
										world.setBlockWithNotify(x + xxx + 1 - angle%2, y+1 , z + zzz + angle%2, 50);
										world.setBlockWithNotify(x + xxx - 1 + angle%2, y+1 , z + zzz - angle%2, 50);
									}*/
								}
								break;
							}
						}
					}
				}
			}
			
			
			/*for (int y = 255; y > 32; y--)
			{
				int block = world.getBlockId(x, y-1, z);
				if (block == 2 || block == 12)
				{
					world.setBlockAndMetadata(x, y, z, 522, random.nextInt(4));
					world.setBlockAndMetadata(x, y-2, z, 52, 54);
					TileEntityMobSpawner var19 = (TileEntityMobSpawner)world.getBlockTileEntity(x, y-2, z);
					var19.setMobID("Zombie");
					break;
				}
				else if (block == 8 || block == 9)
				{
					break;
				}
			}*/
		}
	    // block X location, block Y location, block Z location, block id (in this case, wooden planks)
	}
	
}
