package com.kevin.localServerSkins.proxy;

import java.lang.reflect.Field;

import com.kevin.localServerSkins.helpers.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy {
	
	
	/**
	 * Uses reflect for changing locationSkin which is a private field of the player
	 * 
	 * @param player
	 * @param newSkin ResourceLocation to the new skin
	 * @return Success status
	 */
	@Override
	public void changeSkin(ResourceLocation newSkin) {
		
		//Get the player
		AbstractClientPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if (player != null){
		
			try
			{
				//Use reflection magic to change his private field locationSkin
				Field[] allFields = AbstractClientPlayer.class.getDeclaredFields();
				for (Field field : allFields){
					if (field.getName().equals("locationSkin")){
						Field locationSkin = field;
						locationSkin.setAccessible(true);
						locationSkin.set(player, newSkin);
					}
				}
			} catch (Throwable e) {
				Log.error("Could not change the locationSkin field");
			}
		} else {
			Log.error("The player is not ready");
		}
	}
	
}
