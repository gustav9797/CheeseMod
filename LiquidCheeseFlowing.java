package com.github.gustav9797.CheeseMod;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LiquidCheeseFlowing extends BlockFlowing {

    protected LiquidCheeseFlowing(int par1, Material material) {
        super(par1, material);
    
        this.blockHardness = 100.0F;
        this.setLightOpacity(2);
                
    }
    
    /*@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.theIcon = new Icon[] {
                iconRegister.registerIcon("modid:stillTexture"),
                iconRegister.registerIcon("modid:flowingTexture") };
    }*/

}