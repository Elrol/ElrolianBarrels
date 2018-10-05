package com.github.elrol.ElrolianBarrels.inventories;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;


public class BarrelInventory {

	public static Inventory tier1Inventory() {
		ElrolianBarrels plugin = ElrolianBarrels.getPlugin(ElrolianBarrels.class);
		Inventory tier1 = plugin.getServer().createInventory(null, 1, ChatColor.DARK_GRAY + "Tier 1 Barrel");
		
		return tier1;
	}
	
}
