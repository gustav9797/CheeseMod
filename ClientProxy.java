package com.github.gustav9797.CheeseMod;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
    	MinecraftForgeClient.preloadTexture("/CheeseMod/terrain.png");
    	MinecraftForgeClient.preloadTexture("/CheeseMod/mob/gravelZombie.png");
    }
}
