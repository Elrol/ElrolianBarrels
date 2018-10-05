package com.github.elrol.ElrolianBarrels.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;

public class ConfigRegistry {

	public static int tier1StackLimit;
	public static int tier2StackLimit;
	public static int tier3StackLimit;
	public static int tier4StackLimit;
	public static int tier5StackLimit;
	public static int tier6StackLimit;
	public static int tier7StackLimit;
	public static int tier0StackLimit;
	
	public static String tier1perm;
	public static String tier2perm;
	public static String tier3perm;
	public static String tier4perm;
	public static String tier5perm;
	public static String tier6perm;
	public static String tier7perm;
	public static String tier0perm;
	
	public static String cmdCreativeBarrel;
	
	public void createConfig(ElrolianBarrels plugin) {
		try {
			if(!plugin.getDataFolder().exists()) {
				plugin.getDataFolder().mkdirs();
			}
			File file = new File(plugin.getDataFolder(), "config.yml");
			if(!file.exists()) {
				plugin.getLogger().info("Config.yml not found, creating!");
				plugin.saveDefaultConfig();
			} else {
				plugin.getLogger().info("Config.yml found, Loading!");
			}
			loadDefualts(plugin);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadDefualts(ElrolianBarrels plugin) {
		FileConfiguration config = plugin.getConfig();
		config.set("barrels.tier-1.stack-limit", 32);
		config.set("barrels.tier-2.stack-limit", 128);
		config.set("barrels.tier-3.stack-limit", 512);
		config.set("barrels.tier-4.stack-limit", 2048);
		config.set("barrels.tier-5.stack-limit", 8192);
		config.set("barrels.tier-6.stack-limit", 32768);
		config.set("barrels.tier-7.stack-limit", 131072);
		config.set("barrels.tier-0.stack-limit", Integer.MAX_VALUE);
		
		config.set("barrels.tier-1.permission", "barrel.tier.1");
		config.set("barrels.tier-2.permission", "barrel.tier.2");
		config.set("barrels.tier-3.permission", "barrel.tier.3");
		config.set("barrels.tier-4.permission", "barrel.tier.4");
		config.set("barrels.tier-5.permission", "barrel.tier.5");
		config.set("barrels.tier-6.permission", "barrel.tier.6");
		config.set("barrels.tier-7.permission", "barrel.tier.7");
		config.set("barrels.tier-0.permission", "barrel.tier.0");
		config.set("command.creative-barrel", "barrel.creative");
		plugin.saveConfig();
		
		tier1StackLimit = config.getInt("barrels.tier-1.stack-limit");
		tier2StackLimit = config.getInt("barrels.tier-2.stack-limit");
		tier3StackLimit = config.getInt("barrels.tier-3.stack-limit");
		tier4StackLimit = config.getInt("barrels.tier-4.stack-limit");
		tier5StackLimit = config.getInt("barrels.tier-5.stack-limit");
		tier6StackLimit = config.getInt("barrels.tier-6.stack-limit");
		tier7StackLimit = config.getInt("barrels.tier-7.stack-limit");
		tier0StackLimit = config.getInt("barrels.tier-0.stack-limit");
		
		tier1perm = config.getString("barrels.tier-1.permission");
		tier2perm = config.getString("barrels.tier-2.permission");
		tier3perm = config.getString("barrels.tier-3.permission");
		tier4perm = config.getString("barrels.tier-4.permission");
		tier5perm = config.getString("barrels.tier-5.permission");
		tier6perm = config.getString("barrels.tier-6.permission");
		tier7perm = config.getString("barrels.tier-7.permission");
		tier0perm = config.getString("barrels.tier-0.permission");
		cmdCreativeBarrel = config.getString("command.creative-barrel");
	}
	
	public static int getStackLimit(int tier) {
		switch(tier) {
		case 1: return tier1StackLimit;
		case 2: return tier2StackLimit;
		case 3: return tier3StackLimit;
		case 4: return tier4StackLimit;
		case 5: return tier5StackLimit;
		case 6: return tier6StackLimit;
		case 7: return tier7StackLimit;
		case 0: return tier0StackLimit;
		}
		return 0;
	}
	
	public static String getTierPermission(int tier) {
		switch(tier) {
		case 1: return tier1perm;
		case 2: return tier2perm;
		case 3: return tier3perm;
		case 4: return tier4perm;
		case 5: return tier5perm;
		case 6: return tier6perm;
		case 7: return tier7perm;
		case 0: return tier0perm;
		}
		return "";
	}
}
