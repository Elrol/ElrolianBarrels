package com.github.elrol.ElrolianBarrels.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;
import com.github.elrol.ElrolianBarrels.config.ConfigRegistry;
import com.github.elrol.ElrolianBarrels.items.ItemRegistry;
import com.github.elrol.ElrolianBarrels.libs.Methods;


public class EventRegistry implements Listener{
	
	public void register(ElrolianBarrels plugin) {
		plugin.logger().info(ChatColor.AQUA + "Registered Events");
		plugin.getServer().getPluginManager().registerEvents(plugin.getEventRegistry(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ItemFrameEvents(), plugin);
	}
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		for (Entity entity : event.getBlock().getWorld().getNearbyEntities(event.getBlock().getLocation(), 2, 2, 2)) {
            Block block = entity.getLocation().getBlock();
			if (entity instanceof ItemFrame && block.getLocation().equals(event.getBlock().getLocation())) {
                ItemFrame frame = (ItemFrame) entity;
                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
                	event.setCancelled(true);
                	return;
                }
            }
        }
		if(Methods.isBarrel(event.getItemInHand())) {
			ItemStack barrel = event.getItemInHand();
			int tier = Methods.getTier(barrel);
			if(player.hasPermission(ConfigRegistry.getTierPermission(tier)) || Methods.isElrol(event.getPlayer())) {
				World world = player.getWorld();
				BlockFace face = player.getFacing().getOppositeFace();
				Location loc = event.getBlockReplacedState().getLocation().add(face.getModX(), face.getModY(), face.getModZ());
				if(loc.getBlock().getType() != Material.AIR) {
					event.setCancelled(true);
					return;
				}
				ItemFrame frame = ((ItemFrame)world.spawnEntity(loc, EntityType.ITEM_FRAME));
				String name = "Tier " + tier + " Barrel";
				frame.setFacingDirection(face);
				frame.setCustomName(name);
				
				if(barrel.getItemMeta().getLore().size() > 1 && barrel.getItemMeta().getLore().get(barrel.getItemMeta().getLore().size()-1).startsWith("Contains: ")) {
					
					List<Object> barrelStuff = Methods.getItemFromBarrel(barrel);
					ItemStack stack = (ItemStack)barrelStuff.get(0);
					int qty = Integer.parseInt(barrelStuff.get(1).toString());
					ItemMeta meta = stack.getItemMeta();
					List<String> lore = new ArrayList<String>();
					lore.add(meta.getDisplayName());
					if(meta.hasLore())
						lore.addAll(meta.getLore());
					meta.setLore(lore);
					if(tier == 0)
						meta.setDisplayName(ChatColor.WHITE + "" + Character.toString('\u221e'));
					else
						meta.setDisplayName(ChatColor.WHITE + "" + qty);
					
					stack.setItemMeta(meta);
					frame.setItem(stack);
				}
			} else {
				player.sendMessage(ChatColor.RED + "You dont have permission to use the tier " + tier + " barrels");
				event.setCancelled(true);
			}
		} else if(Methods.isCustomItem(event.getItemInHand())){
			player.sendMessage(ChatColor.RED + "You cant place that item. Its needed to craft a Barrel!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		for (Entity entity : event.getBlock().getWorld().getNearbyEntities(event.getBlock().getLocation(), 2, 2, 2)) {
            if (entity instanceof ItemFrame && entity.getLocation().getBlock().getRelative(((ItemFrame) entity).getAttachedFace()).equals(event.getBlock())) {
                ItemFrame frame = (ItemFrame) entity;
                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
                	int tier = Integer.parseInt(frame.getCustomName().split(" ")[1]);
                	if(event.getPlayer().hasPermission(ConfigRegistry.getTierPermission(tier)) || Methods.isElrol(event.getPlayer())) {
	                	ItemStack barrel;
	                	if(event.getBlock().getType().equals(Material.CHISELED_STONE_BRICKS))
	                		barrel = ItemRegistry.tier1barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.IRON_BLOCK))
	                		barrel = ItemRegistry.tier2barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.GOLD_BLOCK))
	                		barrel = ItemRegistry.tier3barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.OBSIDIAN))
	                		barrel = ItemRegistry.tier4barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.DIAMOND_BLOCK))
	                		barrel = ItemRegistry.tier5barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.EMERALD_BLOCK))
	                		barrel = ItemRegistry.tier6barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.END_STONE))
	                		barrel = ItemRegistry.tier7barrel().clone();
	                	else if(event.getBlock().getType().equals(Material.BEACON))
	                		barrel = ItemRegistry.tier0barrel().clone();
	                	else {
	                		barrel = null;
	                		event.setCancelled(true);
	                		return;
	                	}
	                	Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
	                	ItemStack untranslatedStack = frame.getItem();
	    				ItemStack translatedStack = untranslatedStack.clone();
	    				ItemMeta meta = translatedStack.getItemMeta();
	    				if(!frame.getItem().getType().equals(Material.AIR)) {
	    					int currentAmount;
	    					if(tier == 0)
	    						currentAmount = Integer.MAX_VALUE;
	    					else
	    						currentAmount = Integer.parseInt(ChatColor.stripColor(untranslatedStack.getItemMeta().getDisplayName()));
		    				if(meta.hasLore()) {
		    					List<String> lore = meta.getLore();
		    					meta.setDisplayName(lore.get(0));
		    					lore.remove(0);
		    					meta.setLore(lore);
		    				}
		    				translatedStack.setItemMeta(meta);
		                	
		    				loc.getWorld().dropItem(loc, Methods.addItemToBarrel(translatedStack, barrel, currentAmount));
		                	event.getBlock().setType(Material.AIR);
		                	frame.remove();
		                    break;
	    				} else {
	    					loc.getWorld().dropItem(loc, barrel);
		                	event.getBlock().setType(Material.AIR);
		                	frame.remove();
		                    break;
	    				}
    				} else {
    					event.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to break Tier " + tier + " Barrels");
    					event.setCancelled(true);
    				}
                }
            }
        }
	}
	
	@EventHandler
	public void breakFrame(HangingBreakEvent event) {
		if(event.getEntity() instanceof ItemFrame) {
			ItemFrame frame = (ItemFrame)event.getEntity();
			if(frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")){
				event.setCancelled(true);
				System.out.println("Canceled Break");
			}
		}
	}
	
	@EventHandler
	public void craftItem(PrepareItemCraftEvent event) {
		CraftingInventory inv = event.getInventory();
		for(ItemStack stack : inv.getStorageContents()) {
			if(stack != null && event.getRecipe() != null) {
				if(Methods.isBarrel(event.getRecipe().getResult())) {
					int tier = Methods.getTier(event.getRecipe().getResult());
					for(HumanEntity human : event.getViewers()) {
						if(human.hasPermission(ConfigRegistry.getTierPermission(tier)) || Methods.isElrol((Player)human)) {
							return;
						} else {
							inv.setResult(null);
							human.sendMessage(ChatColor.RED + "You dont have permission to craft a Tier " + tier + " Barrel");
							return;
						}	
					}
					if(!Methods.isCustomItem(stack)) {
						inv.setResult(null);
						return;
					}
				} else if(Methods.isCustomItem(stack) && !Methods.isCustomItem(event.getRecipe().getResult())) {
					inv.setResult(null);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void pistonExtendEvent(BlockPistonExtendEvent event) {
		event.getBlocks().forEach(block -> {
			for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2)) {
	            if (entity instanceof ItemFrame) {
	                ItemFrame frame = (ItemFrame) entity;
	                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
	                	event.setCancelled(true);
	                	System.out.println("Piston extended Barrel, canceled");
	                	return;
	                }
	            }
			}
		});
		Block block = event.getBlock().getRelative(event.getDirection());
		for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 1, 1, 1)) {
            if (entity instanceof ItemFrame) {
                ItemFrame frame = (ItemFrame) entity;
                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
                	event.setCancelled(true);
                	System.out.println("Piston extended Barrel, canceled");
                	return;
                }
            }
		}
	}
	
	@EventHandler
	public void pistonRetractEvent(BlockPistonRetractEvent event) {
		event.getBlocks().forEach(block -> {
			for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2)) {
	            if (entity instanceof ItemFrame && entity.getLocation().getBlock().getRelative(((ItemFrame) entity).getAttachedFace()).equals(block)) {
	                ItemFrame frame = (ItemFrame) entity;
	                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
	                	event.setCancelled(true);
	                	System.out.println("Piston retracted Barrel, canceled");
	                	return;
	                }
	            }
			}
		});
	}
	
	@EventHandler
	public void blockExplode(BlockExplodeEvent event) {
		List<Block> removeBlocks = new ArrayList<Block>();
		event.blockList().forEach(block -> {
			for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2)) {
	            if (entity instanceof ItemFrame && entity.getLocation().getBlock().getRelative(((ItemFrame) entity).getAttachedFace()).equals(block)) {
	                ItemFrame frame = (ItemFrame) entity;
	                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
	                	removeBlocks.add(block);
	                	removeBlocks.add(frame.getLocation().getBlock());
	                	System.out.println("Block Exploded Barrel, canceled");
	                }
	            }
			}
		});
		event.blockList().removeAll(removeBlocks);
	}
	
	@EventHandler
	public void entityExplode(EntityExplodeEvent event) {
		List<Block> removeBlocks = new ArrayList<Block>();
		event.blockList().forEach(block -> {
			for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2)) {
	            if (entity instanceof ItemFrame && entity.getLocation().getBlock().getRelative(((ItemFrame) entity).getAttachedFace()).equals(block)) {
	                ItemFrame frame = (ItemFrame) entity;
	                if (frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
	                	removeBlocks.add(block);
	                	removeBlocks.add(frame.getLocation().getBlock());
	                	System.out.println("Entity Exploded Barrel, canceled");
	                }
	            }
			}
		});
		event.blockList().removeAll(removeBlocks);
		if(event.getEntity() instanceof ItemFrame)
			System.out.println("WAT");
	}
}
