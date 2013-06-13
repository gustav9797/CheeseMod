package com.github.gustav9797.CheeseMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGraveZombie extends EntityZombie {
	public EntityGraveZombie(World world)
	{
		super(world);
		this.texture = "/CheeseMod/mob/graveZombie.png";
		this.moveSpeed = 2.0F;
		this.isImmuneToFire = true;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.moveSpeed = 8.0F;
	}
	
	@Override
	protected int getDropItemId()
	{
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
		return super.getAttackStrength(entity)*2;
    }
	
    @SideOnly(Side.CLIENT)
    public String getTexture()
    {
        return "CheeseMod/mob/gravelZombie.png";
    }
	
	/*@Override
	public void onLivingUpdate()
    {
		((EntityMob)this).onLivingUpdate();
    }*/
}
