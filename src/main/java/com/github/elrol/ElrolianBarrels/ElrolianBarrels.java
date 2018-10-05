package com.github.elrol.ElrolianBarrels;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.elrol.ElrolianBarrels.commands.CommandRegistry;
import com.github.elrol.ElrolianBarrels.config.ConfigRegistry;
import com.github.elrol.ElrolianBarrels.events.EventRegistry;
import com.github.elrol.ElrolianBarrels.recipes.RecipeRegistry;

public class ElrolianBarrels extends JavaPlugin{
	
	//Class Initialization
	private ConfigRegistry cfgReg = new ConfigRegistry();
	private CommandRegistry cmdReg = new CommandRegistry();
	private RecipeRegistry rcpReg = new RecipeRegistry();
	private EventRegistry evtReg = new EventRegistry();
	
	public static boolean isTownyEnabled = false;
	
	private Logger logger = getLogger();
	
	@Override
	public void onEnable() {
		logger.info("Elrolian Barrels Starting!");
		cfgReg.createConfig(this);
		cmdReg.registerCommands(this);
		rcpReg.registerRecipes(this);
		evtReg.register(this);
		
		isTownyEnabled = getServer().getPluginManager().isPluginEnabled("Towny");
		if(isTownyEnabled)
			logger.info("Towny exists, hooking into perms");
		else
			logger.info("Towny not found, ignoring perms");
	}

	@Override
	public void onDisable() {
		logger.info("Elrolian Barrels Shutting down!");
	}
	
	public Logger logger() {return logger;}
	public ConfigRegistry getConfigRegistry() {return this.cfgReg;}
	public CommandRegistry getCommandRegistry() {return this.cmdReg;}
	public RecipeRegistry getRecipeRegistry() {return this.rcpReg;}
	public EventRegistry getEventRegistry() {return this.evtReg;}
}
