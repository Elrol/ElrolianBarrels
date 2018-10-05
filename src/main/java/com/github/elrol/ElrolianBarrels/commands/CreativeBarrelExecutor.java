package com.github.elrol.ElrolianBarrels.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.elrol.ElrolianBarrels.config.ConfigRegistry;
import com.github.elrol.ElrolianBarrels.items.ItemRegistry;

public class CreativeBarrelExecutor implements CommandExecutor {

	String command;
	
	public CreativeBarrelExecutor(String command) {
		this.command = command;
	}
	
	@Override
	public boolean onCommand(CommandSender src, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase(command) && args.length > 0 && Integer.parseInt(args[0]) > -1 && Integer.parseInt(args[0]) < 8 ) {
			if(src instanceof Player){
				Player player = (Player)src;
				int tier = Integer.parseInt(args[0]);
				if(player.hasPermission(ConfigRegistry.cmdCreativeBarrel)) {
					ItemStack barrel;
					if(tier == 0)
						barrel = ItemRegistry.tier0barrel();
					else if(tier == 2)
						barrel = ItemRegistry.tier2barrel();
					else if(tier == 3)
						barrel = ItemRegistry.tier3barrel();
					else if(tier == 4)
						barrel = ItemRegistry.tier4barrel();
					else if(tier == 5)
						barrel = ItemRegistry.tier5barrel();
					else if(tier == 6)
						barrel = ItemRegistry.tier6barrel();
					else if(tier == 7)
						barrel = ItemRegistry.tier7barrel();
					else
						barrel = ItemRegistry.tier1barrel();
					player.getInventory().addItem(barrel);
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
				}
			} else {
				src.sendMessage(ChatColor.RED + "You must be a player to use this command.");
			}
		}
		return false;
	}

}
