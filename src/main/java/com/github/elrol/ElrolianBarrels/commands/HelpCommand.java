package com.github.elrol.ElrolianBarrels.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.elrol.ElrolianBarrels.libs.Methods;

public class HelpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender src, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("healme")) {
			if(src instanceof Player){
				Player player = (Player)src;
				if(Methods.isBarrel(player.getEquipment().getItemInMainHand())) {
					ItemStack mainHand = player.getEquipment().getItemInMainHand();
					if(!player.getEquipment().getItemInOffHand().getType().equals(Material.AIR)) {
						ItemStack offHand = player.getEquipment().getItemInOffHand();
						player.getEquipment().setItemInMainHand(Methods.setBarrelContents(mainHand, offHand, offHand.getAmount()));
						player.getEquipment().setItemInOffHand(null);
						System.out.println("Success?");
					} else {
						player.getEquipment().setItemInMainHand(Methods.getBarrelContents(player, mainHand));
						System.out.println("Added items to your inv?");
					}
				} else {
					return true;
				}
			} else {
				src.sendMessage(ChatColor.RED + "Only players can execute this command");
				return true;
			}
			src.sendMessage(ChatColor.GREEN + "Stored Item");
		}
		return true;
	}

}
