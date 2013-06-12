package com.github.gustav9797.CheeseMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGraveZombie extends EntityZombie {
	public EntityGraveZombie(World world)
	{
		super(world);
		this.texture = "CheeseMod/mob/graveZombie.png";
		this.moveSpeed = 32.0F;
		this.isImmuneToFire = true;
	}
	
	@Override
	protected int getDropItemId()
	{
	  //This is the item your mob will drop
	  return 0;
	}

	@Override
    public int getMaxHealth()
    {
        return 1;
    }
	
	@Override
    public int getAttackStrength(Entity entity)
    {
		return super.getAttackStrength(entity)/2;
    }
	
    @SideOnly(Side.CLIENT)
    public String getTexture()
    {
        return "CheeseMod/mob/graveZombie.png";
    }
	
	/*@Override
	public void onLivingUpdate()
    {
		((EntityMob)this).onLivingUpdate();
    }*/
}
