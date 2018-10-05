package com.github.elrol.ElrolianBarrels.commands;

import org.bukkit.command.CommandExecutor;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;

public class CommandRegistry {
	
	public ElrolianBarrels plugin;

	private final String giveBarrel = "GiveBarrel";
	
	public void registerCommands(ElrolianBarrels plugin) {
		this.plugin = plugin;
		register(giveBarrel, new CreativeBarrelExecutor(giveBarrel));
	}
	
	private void register(String cmd, CommandExecutor executor){
		plugin.getLogger().info("Registering the /" + cmd + " command.");
		plugin.getCommand(cmd).setExecutor(executor);
	}

}
