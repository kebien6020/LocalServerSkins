package com.kevin.localServerSkins.helpers;

import org.apache.logging.log4j.Level;

import com.kevin.localServerSkins.reference.Reference;

import cpw.mods.fml.common.FMLLog;

public class Log {
	
	public static void log(Level level, String data){
		FMLLog.log(Reference.MOD_ID, level, data);
	}
	
	public static void info(String data){
		log(Level.INFO, data);
	}

	public static void error(String data) {
		log(Level.ERROR, data);
		
	}

}
