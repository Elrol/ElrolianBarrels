package com.github.elrol.ElrolianBarrels.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemRegistry implements Listener{
	
	public static List<ItemStack> customItems = new ArrayList<ItemStack>();
	
	public static ItemStack tier0barrel() {
		ItemStack barrel = new ItemStack(Material.BEACON);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 0 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;
	}
	
	public static ItemStack tier1wall() {
		ItemStack wall = new ItemStack(Material.COBBLESTONE);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_GRAY + "Tier 1 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 1 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier1frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_GRAY + "Tier 1 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 1 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier1barrel() {
		ItemStack barrel = new ItemStack(Material.CHISELED_STONE_BRICKS);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 1 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}
	
	public static ItemStack tier2wall() {
		ItemStack wall = new ItemStack(Material.IRON_BLOCK);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Tier 2 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 2 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier2frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "Tier 2 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 2 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier2barrel() {
		ItemStack barrel = new ItemStack(Material.IRON_BLOCK);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 2 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}

	public static ItemStack tier3wall() {
		ItemStack wall = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 3 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 3 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier3frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 3 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 3 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier3barrel() {
		ItemStack barrel = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 3 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}

	public static ItemStack tier4wall() {
		ItemStack wall = new ItemStack(Material.OBSIDIAN);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_PURPLE + "Tier 4 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 4 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier4frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_PURPLE + "Tier 4 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 4 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier4barrel() {
		ItemStack barrel = new ItemStack(Material.OBSIDIAN);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 4 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}
	
	public static ItemStack tier5wall() {
		ItemStack wall = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + "Tier 5 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 5 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier5frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + "Tier 5 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 5 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier5barrel() {
		ItemStack barrel = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 5 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}
	public static ItemStack tier6wall() {
		ItemStack wall = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Tier 6 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 6 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier6frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Tier 6 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 6 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier6barrel() {
		ItemStack barrel = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 6 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}
	
	public static ItemStack tier7wall() {
		ItemStack wall = new ItemStack(Material.END_STONE);
		ItemMeta meta = wall.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Tier 7 Wall");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 7 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wall.setItemMeta(meta);
		customItems.add(wall);
		return wall;
	};
	
	public static ItemStack tier7frame() {
		ItemStack frame = new ItemStack(Material.ITEM_FRAME);
		ItemMeta meta = frame.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Tier 7 Frame");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Used to make a Tier 7 Barrel");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		frame.setItemMeta(meta);
		customItems.add(frame);
		return frame;
	}
	
	public static ItemStack tier7barrel() {
		ItemStack barrel = new ItemStack(Material.END_STONE);
		ItemMeta meta = barrel.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Tier 7 Barrel");
		ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.WHITE + "Can be used to store lots of items.");
			meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		barrel.setItemMeta(meta);
		customItems.add(barrel);
		return barrel;	
	}
}
