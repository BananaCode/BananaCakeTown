package com.ubempire.caketown;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.ubempire.caketown.gen.CakeTownGenerator;

public class CakeTown extends JavaPlugin {

	@Override
	public void onDisable() {
		System.out.println("[BananaCakeTown] The cake is a lie :(");
	}

	@Override
	public void onEnable() {
		System.out.println("[BananaCakeTown] OMG CAEK!");
	}
	
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new CakeTownGenerator();
    }
}
