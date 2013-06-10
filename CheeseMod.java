package com.github.gustav9797.CheeseMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="CheeseMod", name="CheeseMod", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)

public class CheeseMod {
	private int[] ids;
	
	public static Block cheese;
	public static Block infectedCheese;
	
	@Instance("CheeseMod")
    public static CheeseMod instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="com.github.gustav9797.CheeseMod.ClientProxy", serverSide="com.github.gustav9797.CheeseMod.CommonProxy")
    public static CommonProxy proxy;
    
    @PreInit
	public void preLoad(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		ids = new int[] {
				config.getBlock("blocks", "cheeseBlock", 2301).getInt(),
				config.getBlock("blocks", "infectedCheeseBlock", 2302).getInt(),

		};
		config.save();

		MinecraftForge.EVENT_BUS.register(proxy);
	}
    
    @Init
    public void load(FMLInitializationEvent event) {
    	
    	cheese = new CheeseBlock(ids[0], 0, Material.ground, ids[1])
        .setHardness(0.5F).setStepSound(Block.soundClothFootstep)
        .setBlockName("cheese").setCreativeTab(CreativeTabs.tabDecorations);
    	
        LanguageRegistry.addName(cheese, "Cheese");
        MinecraftForge.setBlockHarvestLevel(cheese, "shovel", 0);
        GameRegistry.registerBlock(cheese, "cheese");
    	
    	infectedCheese = new InfectedCheeseBlock(ids[1], 1, Material.ground)
        .setHardness(0.5F).setStepSound(Block.soundClothFootstep)
        .setBlockName("cheese").setCreativeTab(CreativeTabs.tabDecorations);
        
        LanguageRegistry.addName(infectedCheese, "Infected Cheese");
        MinecraftForge.setBlockHarvestLevel(infectedCheese, "shovel", 0);
        GameRegistry.registerBlock(infectedCheese, "infectedCheese");
       
        // End Basic Blocks
        
            proxy.registerRenderers();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
}
