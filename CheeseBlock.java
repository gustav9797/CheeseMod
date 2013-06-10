package com.github.gustav9797.CheeseMod;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CheeseBlock extends Block {
	int infectedId = 0;
	int[] xLoop = { 1, -1, 0, 0, 0, 0 };
	int[] yLoop = { 0, 0, 1, -1, 0, 0 };
	int[] zLoop = { 0, 0, 0, 0, 1, -1 };
	int[] blocksToTurnCheese = { stone.blockID, dirt.blockID, sand.blockID,
			gravel.blockID };

	Random random = new Random();

	public CheeseBlock(int id, int texture, Material material, int infectedId) {
		super(id, texture, material);
		this.infectedId = infectedId;
		super.setTickRandomly(true);
	}

	@Override
	public String getTextureFile() {
		return "/CheeseMod/terrain.png";
	}

	public int tickRate() {
		return 2;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("Stop annoying me! I am Mr. Cheese number " + getMetadata(world, x, y, z));
		return false;
	}
	
	public int getMetadata(World world, int x, int y, int z){
		return(world.getBlockMetadata(x, y, z));
	}

	double calcDist(Vector3f v1, Vector3f v2) {
		Vector3f v = new Vector3f();
		v.x = v1.x - v2.x;
		v.y = v1.y - v2.y;
		v.z = v1.z - v2.z;
		return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
	}

	private void turnCheeseIntoInfected(World world, int amount, int x, int y,
			int z, int firstx, int firsty, int firstz) {

		for (int i = 0; i < 6; i++) {
			try {
				int xVar = xLoop[i];
				int yVar = yLoop[i];
				int zVar = zLoop[i];
				// System.out.println(xVar + " " + yVar + "" + zVar);

				if (world.getBlockId(x + xVar, y + yVar, z + zVar) == super.blockID
						&& calcDist(new Vector3f(firstx, firsty, firstz),
								new Vector3f(x + xVar, y + yVar, z + zVar)) < (amount - 1 + random
								.nextInt(80))) {
					world.setBlock(x + xVar, y + yVar, z + zVar, infectedId);
					turnCheeseIntoInfected(world, amount, x + xVar, y + yVar, z
							+ zVar, firstx, firsty, firstz);
				}
			} catch (Throwable e) {
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) < 4) {
			int side = random.nextInt(5);
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
				zToSpread -= 1;
				break;
			case 4:
				zToSpread += 1;
				break;
			}
			
			int up = random.nextInt(150);
			if(up == 1){
				yToSpread = 1;
			}

			int xSpreadPos = x + xToSpread;
			int ySpreadPos = y + yToSpread;
			int zSpreadPos = z + zToSpread;

			for (int i = 0; i < 6; i++) {
				int xVar = xLoop[i];
				int yVar = yLoop[i];
				int zVar = zLoop[i];
				int tempId = world.getBlockId(x + xVar, y + yVar, z + zVar);
				if (tempId == mushroomRed.blockID
						|| tempId == mushroomBrown.blockID) {
					turnCheeseIntoInfected(world, 3, x, y, z, x, y, z);
				}
			}

			try {

				if (world.getBlockId(xSpreadPos, ySpreadPos, zSpreadPos) != super.blockID
						&& world.setBlock(xSpreadPos, ySpreadPos, zSpreadPos,
								super.blockID)) {
					world.setBlockMetadata(xSpreadPos, ySpreadPos, zSpreadPos,
							(world.getBlockMetadata(x, y, z) + 1));

				}
			} catch (Throwable e) {
			}

		}
	}

}
