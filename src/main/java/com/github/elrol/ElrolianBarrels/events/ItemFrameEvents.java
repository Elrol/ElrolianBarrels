package com.github.elrol.ElrolianBarrels.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;
import com.github.elrol.ElrolianBarrels.config.ConfigRegistry;
import com.github.elrol.ElrolianBarrels.libs.Methods;

import net.md_5.bungee.api.ChatColor;


public class ItemFrameEvents implements Listener{
	
	@EventHandler
	public void onItemFrameAttack(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player)event.getDamager();
			if(event.getEntity() instanceof ItemFrame) {
				ItemFrame frame = (ItemFrame)event.getEntity();
				if(frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
					int tier = Integer.parseInt(frame.getCustomName().split(" ")[1]);
					if(player.hasPermission(ConfigRegistry.getTierPermission(tier)) || Methods.isElrol(player)) {
						if(ElrolianBarrels.isTownyEnabled && !Methods.getTownyPermsForSwitch(player, frame.getLocation().getBlock())) {
							event.setCancelled(true);
							return;
						} else if(event.isCancelled()) {
							return;
						}
						ItemStack untranslatedStack = frame.getItem();
						ItemStack translatedStack = untranslatedStack.clone();
						ItemMeta meta = translatedStack.getItemMeta();
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
						int qty = 1;
						if(!player.isSneaking()) {
							qty = translatedStack.getMaxStackSize();
						}
						if(currentAmount <= qty)
							qty = currentAmount;
						translatedStack.setAmount(qty);
						Location loc = frame.getLocation().getBlock().getLocation().add(new Location(frame.getWorld(), 0.5, 0.5, 0.5));
						Item item = player.getWorld().dropItem(loc, translatedStack);
						item.setVelocity(new Vector(0, 0,0));
						item.teleport(loc);
						if(tier == 0) {
							event.setCancelled(true);
							return;
						}
						ItemMeta untranslatedMeta = untranslatedStack.getItemMeta();
						untranslatedMeta.setDisplayName(ChatColor.WHITE + "" + (currentAmount - qty));
						untranslatedStack.setItemMeta(untranslatedMeta);
						if(currentAmount - qty == 0) {
							frame.setItem(null);
						} else if(currentAmount - qty > 0) {
							frame.setItem(untranslatedStack);
						} else {
							player.sendMessage(ChatColor.RED + "Something REALLY wrong has happened");
							frame.setItem(null);
						}
						event.setCancelled(true);
					} else {
						player.sendMessage(ChatColor.RED + "You do not have permission to withdraw items from a Tier " + tier + " Barrel");
						event.setCancelled(true);
					}
				}
			}
		} else {
			if(event.getEntity() instanceof ItemFrame) {
				ItemFrame frame = (ItemFrame)event.getEntity();
				if(frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
					event.setCancelled(true);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onItemFrameDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof ItemFrame) {
			ItemFrame frame = (ItemFrame)event.getEntity();
			if(frame.getCustomName() != null && ChatColor.stripColor(frame.getCustomName()).startsWith("Tier ")) {
				//event.setCancelled(true);
				return;
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemFrameActivate(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		
		if(entity instanceof ItemFrame) {
			ItemFrame frame = (ItemFrame)entity;
			if(frame.getCustomName() != null) {
				if(((ItemFrame)entity).getCustomName().startsWith("Tier ")) {
					if(ElrolianBarrels.isTownyEnabled && !Methods.getTownyPermsForSwitch(player, frame.getLocation().getBlock())) {
						event.setCancelled(true);
						return;
					} else if(event.isCancelled()) {
						return;
					}
					int tier = Integer.parseInt(frame.getCustomName().split(" ")[1]);
					ItemStack stack = frame.getItem();
					if(player.hasPermission(ConfigRegistry.getTierPermission(tier)) || Methods.isElrol(event.getPlayer())) {
						//If itemStack in frame exists
						if(!stack.getType().equals(Material.AIR)) {
							if(tier == 0) {
								event.setCancelled(true);
								return;
							}
							if(stack.getItemMeta() != null && stack.getItemMeta().hasLore()) {
								ItemStack translatedStack = stack.clone();
								ItemMeta meta = translatedStack.getItemMeta();
								ItemStack mainHand = player.getInventory().getItemInMainHand();
								List<String> lore = meta.getLore();
								meta.setDisplayName(lore.get(0));
								lore.remove(0);
								meta.setLore(lore);
								translatedStack.setItemMeta(meta);
								//If player is sneaking
								if(player.isSneaking()) {
									for(ItemStack slot : player.getInventory().getStorageContents()) {
										if(slot != null) {
											if(slot.hasItemMeta()) {
												if(slot.getItemMeta().equals(translatedStack.getItemMeta())) {
													int qty = Integer.parseInt(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
													int maxStackLimit = ConfigRegistry.getStackLimit(tier) * stack.getMaxStackSize();
													if(qty == maxStackLimit) {
														event.setCancelled(true);
														return;
													} else if(qty + slot.getAmount() > maxStackLimit) {
														int difference = qty + slot.getAmount() - maxStackLimit;
														ItemMeta stackMeta = stack.getItemMeta();
														stackMeta.setDisplayName(ChatColor.WHITE + "" + maxStackLimit);
														stack.setItemMeta(stackMeta);
														frame.setItem(stack);
														if(!player.getGameMode().equals(GameMode.CREATIVE))
															slot.setAmount(difference);
														event.setCancelled(true);
														return;
													} else {
														qty += slot.getAmount();
														ItemMeta stackMeta = stack.getItemMeta();
														stackMeta.setDisplayName(ChatColor.WHITE + "" + qty);
														stack.setItemMeta(stackMeta);
														frame.setItem(stack);
														if(!player.getGameMode().equals(GameMode.CREATIVE))
															player.getInventory().remove(slot);
													}
												}
											} else {
												if(slot.getType().equals(translatedStack.getType()) && (slot.getDurability() == stack.getDurability())) {
													int qty = Integer.parseInt(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
													int maxStackLimit = ConfigRegistry.getStackLimit(tier) * stack.getMaxStackSize();
													if(qty == maxStackLimit) {
														event.setCancelled(true);
														return;
													} else if(qty + slot.getAmount() > maxStackLimit) {
														int difference = qty + slot.getAmount() - maxStackLimit;
														ItemMeta stackMeta = stack.getItemMeta();
														stackMeta.setDisplayName(ChatColor.WHITE + "" + maxStackLimit);
														stack.setItemMeta(stackMeta);
														frame.setItem(stack);
														if(!player.getGameMode().equals(GameMode.CREATIVE))
															slot.setAmount(difference);
														event.setCancelled(true);
														return;
													} else {
														qty += slot.getAmount();
														ItemMeta stackMeta = stack.getItemMeta();
														stackMeta.setDisplayName(ChatColor.WHITE + "" + qty);
														stack.setItemMeta(stackMeta);
														frame.setItem(stack);
														if(!player.getGameMode().equals(GameMode.CREATIVE))
															player.getInventory().remove(slot);
													}
												}
											}
										}
									}
								//If the player is not sneaking
								} else {
									//If the held item is the same item in the frame
									if(mainHand.getItemMeta() != null && mainHand.getItemMeta().equals(translatedStack.getItemMeta()) && mainHand.getType().equals(translatedStack.getType())) {
										int qty = Integer.parseInt(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
										int maxStackLimit = ConfigRegistry.getStackLimit(tier) * stack.getMaxStackSize();
										if(qty == maxStackLimit) {
											event.setCancelled(true);
											return;
										} else if(qty + mainHand.getAmount() > maxStackLimit) {
											int difference = qty + mainHand.getAmount() - maxStackLimit;
											ItemMeta stackMeta = stack.getItemMeta();
											stackMeta.setDisplayName(ChatColor.WHITE + "" + maxStackLimit);
											stack.setItemMeta(stackMeta);
											frame.setItem(stack);
											if(!player.getGameMode().equals(GameMode.CREATIVE))
												mainHand.setAmount(difference);
										} else {
											qty += mainHand.getAmount();
											ItemMeta stackMeta = stack.getItemMeta();
											stackMeta.setDisplayName(ChatColor.WHITE + "" + qty);
											stack.setItemMeta(stackMeta);
											frame.setItem(stack);
											if(!player.getGameMode().equals(GameMode.CREATIVE))
												player.getInventory().setItemInMainHand(null);
										}
									}
								}
							//If stack does not have lore
							} else {
								ItemStack translatedStack = stack.clone();
								ItemMeta meta = translatedStack.getItemMeta();
								ItemStack mainHand = player.getInventory().getItemInMainHand();
								meta.setDisplayName(stack.getType().name());
								translatedStack.setItemMeta(meta);
								//if player is sneaking
								if(player.isSneaking()) {
									for(ItemStack slot : player.getInventory().getStorageContents()) {
										//if the slot contains the same item as the frame
										if(slot != null && slot.getItemMeta() != null && slot.getItemMeta().equals(translatedStack.getItemMeta())) {
											int qty = Integer.parseInt(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
											int maxStackLimit = ConfigRegistry.getStackLimit(tier) * stack.getMaxStackSize();
											if(qty == maxStackLimit) {
												event.setCancelled(true);
												return;
											} else if(qty + mainHand.getAmount() > maxStackLimit) {
												int difference = qty + mainHand.getAmount() - maxStackLimit;
												ItemMeta stackMeta = stack.getItemMeta();
												stackMeta.setDisplayName(ChatColor.WHITE + "" + maxStackLimit);
												stack.setItemMeta(stackMeta);
												frame.setItem(stack);
												if(!player.getGameMode().equals(GameMode.CREATIVE))
													mainHand.setAmount(difference);
												event.setCancelled(true);
												return;
											} else {
												qty += mainHand.getAmount();
												ItemMeta stackMeta = stack.getItemMeta();
												stackMeta.setDisplayName(ChatColor.WHITE + "" + qty);
												stack.setItemMeta(stackMeta);
												frame.setItem(stack);
												if(!player.getGameMode().equals(GameMode.CREATIVE))
													player.getInventory().setItemInMainHand(null);
											}
										}
									}
								//if the player is not sneaking	
								} else {
									//if the held item is the item in the frame
									if(mainHand.getItemMeta() != null && mainHand.getItemMeta().equals(translatedStack.getItemMeta())) {
										int qty = Integer.parseInt(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
										int maxStackLimit = ConfigRegistry.getStackLimit(tier) * stack.getMaxStackSize();
										if(qty == maxStackLimit) {
											event.setCancelled(true);
											return;
										} else if(qty + mainHand.getAmount() > maxStackLimit) {
											int difference = qty + mainHand.getAmount() - maxStackLimit;
											ItemMeta stackMeta = stack.getItemMeta();
											stackMeta.setDisplayName(ChatColor.WHITE + "" + maxStackLimit);
											stack.setItemMeta(stackMeta);
											frame.setItem(stack);
											if(!player.getGameMode().equals(GameMode.CREATIVE))
												mainHand.setAmount(difference);
										} else {
											qty += mainHand.getAmount();
											ItemMeta stackMeta = stack.getItemMeta();
											stackMeta.setDisplayName(ChatColor.WHITE + "" + qty);
											stack.setItemMeta(stackMeta);
											frame.setItem(stack);
											if(!player.getGameMode().equals(GameMode.CREATIVE))
												player.getInventory().setItemInMainHand(null);
										}
									}
								}
							}
							event.setCancelled(true);
						//if the frame doesnt contain an itemstack
						} else {
							ItemStack copy = player.getInventory().getItemInMainHand().clone();
							ItemMeta meta = copy.getItemMeta();
							List<String> lore = new ArrayList<String>();
							if(meta.getDisplayName() != null)
								lore.add(meta.getDisplayName());
							if(meta.hasLore())
								lore.addAll(meta.getLore());
							meta.setLore(lore);
							if(tier == 0)
								meta.setDisplayName(ChatColor.WHITE + "" + Character.toString('\u221e'));
							else if(player.getGameMode().equals(GameMode.CREATIVE))
								meta.setDisplayName(ChatColor.WHITE + "" + (ConfigRegistry.getStackLimit(tier) * copy.getMaxStackSize()));
							else 
								meta.setDisplayName(ChatColor.WHITE + "" + copy.getAmount());
							copy.setItemMeta(meta);
							frame.setItem(copy);
							if(!player.getGameMode().equals(GameMode.CREATIVE))
								player.getInventory().setItemInMainHand(null);
							event.setCancelled(true);
						}
					} else {
						player.sendMessage(ChatColor.RED + "You do not have permission to deposit items into a Tier " + tier + " Barrel");
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
