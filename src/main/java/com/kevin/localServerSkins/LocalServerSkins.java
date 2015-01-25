package com.kevin.localServerSkins;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import com.kevin.localServerSkins.helpers.Log;
import com.kevin.localServerSkins.proxy.CommonProxy;
import com.kevin.localServerSkins.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class LocalServerSkins {
	
	private File skin = new File("skin.png");
	private boolean skinExists = false;
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		
		skinExists = skin.exists();
	}
	
	@SidedProxy(modId = Reference.MOD_ID, clientSide = "com.kevin.localServerSkins.proxy.ClientProxy", serverSide = "com.kevin.localServerSkins.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	
	//When the player logs in
	@SubscribeEvent
	public void onTrigger(PlayerEvent.ItemPickupEvent event){
		
		if (skinExists) {

			Log.info("Trying to change the skin");

			//Make a BufferedImage from the skin
			/*
			BufferedImage newSkin = null;

			try {
				newSkin = ImageIO.read(skin);
			} catch (IOException e1) {
				Log.error("Couldn't read the skin file.");
				e1.printStackTrace();
			}

			DynamicTexture texture = new DynamicTexture(newSkin);

			texture.getGlTextureId();
			*/

			proxy.changeSkin(new ResourceLocation(Reference.MOD_ID, "textures/skin/skin.png"));

		} else {
			Log.info("Skin not found, skipping");
		}
		
	}
}
