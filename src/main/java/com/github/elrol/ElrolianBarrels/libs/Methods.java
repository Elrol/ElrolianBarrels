package com.github.elrol.ElrolianBarrels.libs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.elrol.ElrolianBarrels.items.ItemRegistry;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class Methods {
	
	public static String elrol = "06fe1033-d391-4d21-8706-410e2c22e8a8";
	
	public static boolean isElrol(Player player) {
		return player.getUniqueId().toString().equals(elrol);
	}
	
	public static boolean isCustomItem(ItemStack stack) {
		if(ItemRegistry.customItems.contains(stack))
			return true;
		return false;
	}
	
	public static boolean isBarrel(ItemStack stack) {
		List<ItemStack> barrels = new ArrayList<ItemStack>();
		barrels.add(ItemRegistry.tier1barrel());
		barrels.add(ItemRegistry.tier2barrel());
		barrels.add(ItemRegistry.tier3barrel());
		barrels.add(ItemRegistry.tier4barrel());
		barrels.add(ItemRegistry.tier5barrel());
		barrels.add(ItemRegistry.tier6barrel());
		barrels.add(ItemRegistry.tier7barrel());
		barrels.add(ItemRegistry.tier0barrel());
		for(ItemStack barrel : barrels) {
			if(barrel.getItemMeta().getDisplayName().equals(stack.getItemMeta().getDisplayName()))
				return true;
		}
		return false;
	}
	
	public static ItemStack setBarrelContents(ItemStack barrel, ItemStack stack, int qty) {
		ItemStack newBarrel = barrel.clone();
		ItemMeta meta = newBarrel.getItemMeta();
		List<String> lore = meta.getLore();
		lore.add("Contains:" + itemToString(stack));
		lore.add(stack.toString());
		meta.setLore(lore);
		newBarrel.setItemMeta(meta);
		return newBarrel;
	}
	
	public static ItemStack getBarrelContents(Player player, ItemStack barrel) {
		ItemStack newBarrel = barrel.clone();
		ItemMeta meta = newBarrel.getItemMeta();
		List<String> lore = meta.getLore();
		for(String line : lore) {
			if(line.startsWith("Contains:")) {
				String[] info = line.split(":");
				ItemStack stack;
				try {
					stack = itemFromString(info[1]);
				} catch(NumberFormatException e) {
					e.printStackTrace();
					return null;
				}
				lore.remove(line);
				player.getInventory().addItem(stack);
				meta.setLore(lore);
				newBarrel.setItemMeta(meta);
			}
		}
		return newBarrel;
	}
	
	public static String itemToString(ItemStack stack) {
		YamlConfiguration config = new YamlConfiguration();
		config.set("i", stack);
		return config.saveToString();
	}
	
	public static ItemStack itemFromString(String string) {
		YamlConfiguration config = new YamlConfiguration();
		try {
			config.loadFromString(string);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return config.getItemStack("i", null);
	}
	
	
	public static int getTier(ItemStack stack) {
		if(isBarrel(stack)) {
			String unformatedText = ChatColor.stripColor(stack.getItemMeta().getDisplayName().split(" ")[1]);
			try {
				return Integer.parseInt(unformatedText);
			} catch (NumberFormatException e) {
				System.out.println(unformatedText);
				e.printStackTrace();
			}
		}
		System.err.println("getTier was invalid");
		return 10;
	}
	
	public static ItemStack addItemToBarrel(ItemStack item, ItemStack barrel, int qty) {
		ItemStack newBarrel = barrel.clone();
		ItemMeta meta = newBarrel.getItemMeta();
    	List<String> lore = meta.getLore();
    	String contents;
    	if(item.getItemMeta().hasDisplayName())
    		contents = item.getItemMeta().getDisplayName();
    	else
   			contents = item.getType().toString().replace("_", " ").toLowerCase() + (qty > 1 ? "s" : "");
       	lore.add("Contains: " + qty + " " + contents);
    	meta.setLore(lore);
    	newBarrel.setItemMeta(meta);

       	net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(newBarrel);
       	NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
       	compound.setString("BarrelContents", ItemStackSerialization.itemToString(item));
       	nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	public static List<Object> getItemFromBarrel(ItemStack barrel) {
		ItemStack newBarrel = barrel.clone();
		ItemMeta meta = newBarrel.getItemMeta();
		
		net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(newBarrel);
		System.out.println("Unpacking Barrel");
       	NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
       	if(!compound.hasKey("BarrelContents")) {
       		System.out.println("Found barrel contents");
       	}
		ItemStack stack = ItemStackSerialization.stringToItem(compound.getString("BarrelContents"));
		Integer qty = Integer.parseInt(meta.getLore().get(meta.getLore().size()-1).split(" ")[1]);
		List<Object> list = new ArrayList<Object>();
		list.add(stack);
		list.add(qty);
		return list;
	}
	
	public static boolean getTownyPermsForSwitch(Player player, Block block) {
		boolean perm = PlayerCacheUtil.getCachePermission(player, block.getLocation(), block.getType(), ActionType.SWITCH);
		System.out.println(perm);
		return perm;
	}
}
