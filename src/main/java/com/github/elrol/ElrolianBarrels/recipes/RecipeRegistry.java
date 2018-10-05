package com.github.elrol.ElrolianBarrels.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import com.github.elrol.ElrolianBarrels.ElrolianBarrels;
import com.github.elrol.ElrolianBarrels.items.ItemRegistry;

import net.md_5.bungee.api.ChatColor;

public class RecipeRegistry {
	
	public void registerRecipes(ElrolianBarrels plugin) {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Registering recipes!");
		ShapedRecipe tier1wall = new ShapedRecipe(new NamespacedKey(plugin, "tier1wall"), ItemRegistry.tier1wall());
			tier1wall.shape(" x ","xyx"," x ");
			tier1wall.setIngredient('x', Material.COBBLESTONE);
			tier1wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier1wall);
		ShapedRecipe tier1frame = new ShapedRecipe(new NamespacedKey(plugin, "tier1frame"), ItemRegistry.tier1frame());
			tier1frame.shape(" x ", "xyx", " x ");
			tier1frame.setIngredient('x', Material.COBBLESTONE);
			tier1frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier1frame);
		ShapedRecipe tier1barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier1barrel"), ItemRegistry.tier1barrel());
			tier1barrel.shape("xxx", "xyx", "xxx");
			tier1barrel.setIngredient('x', ItemRegistry.tier1wall().getData());
			tier1barrel.setIngredient('y', ItemRegistry.tier1frame().getData());
			plugin.getServer().addRecipe(tier1barrel);
			
		ShapedRecipe tier2wall = new ShapedRecipe(new NamespacedKey(plugin, "tier2wall"), ItemRegistry.tier2wall());
			tier2wall.shape(" x ","xyx"," x ");
			tier2wall.setIngredient('x', Material.IRON_INGOT);
			tier2wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier2wall);
		ShapedRecipe tier2frame = new ShapedRecipe(new NamespacedKey(plugin, "tier2frame"), ItemRegistry.tier2frame());
			tier2frame.shape(" x ", "xyx", " x ");
			tier2frame.setIngredient('x', Material.IRON_INGOT);
			tier2frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier2frame);
		ShapedRecipe tier2barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier2barrel"), ItemRegistry.tier2barrel());
			tier2barrel.shape("xxx", "xyx", "xxx");
			tier2barrel.setIngredient('x', ItemRegistry.tier2wall().getData());
			tier2barrel.setIngredient('y', ItemRegistry.tier2frame().getData());
			plugin.getServer().addRecipe(tier2barrel);
			
		ShapedRecipe tier3wall = new ShapedRecipe(new NamespacedKey(plugin, "tier3wall"), ItemRegistry.tier3wall());
			tier3wall.shape(" x ","xyx"," x ");
			tier3wall.setIngredient('x', Material.GOLD_INGOT);
			tier3wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier3wall);
		ShapedRecipe tier3frame = new ShapedRecipe(new NamespacedKey(plugin, "tier3frame"), ItemRegistry.tier3frame());
			tier3frame.shape(" x ", "xyx", " x ");
			tier3frame.setIngredient('x', Material.GOLD_INGOT);
			tier3frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier3frame);
		ShapedRecipe tier3barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier3barrel"), ItemRegistry.tier3barrel());
			tier3barrel.shape("xxx", "xyx", "xxx");
			tier3barrel.setIngredient('x', ItemRegistry.tier3wall().getData());
			tier3barrel.setIngredient('y', ItemRegistry.tier3frame().getData());
			plugin.getServer().addRecipe(tier3barrel);
			
		ShapedRecipe tier4wall = new ShapedRecipe(new NamespacedKey(plugin, "tier4wall"), ItemRegistry.tier4wall());
			tier4wall.shape(" x ","xyx"," x ");
			tier4wall.setIngredient('x', Material.OBSIDIAN);
			tier4wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier4wall);
		ShapedRecipe tier4frame = new ShapedRecipe(new NamespacedKey(plugin, "tier4frame"), ItemRegistry.tier4frame());
			tier4frame.shape(" x ", "xyx", " x ");
			tier4frame.setIngredient('x', Material.OBSIDIAN);
			tier4frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier4frame);
		ShapedRecipe tier4barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier4barrel"), ItemRegistry.tier4barrel());
			tier4barrel.shape("xxx", "xyx", "xxx");
			tier4barrel.setIngredient('x', ItemRegistry.tier4wall().getData());
			tier4barrel.setIngredient('y', ItemRegistry.tier4frame().getData());
			plugin.getServer().addRecipe(tier4barrel);
			
		ShapedRecipe tier5wall = new ShapedRecipe(new NamespacedKey(plugin, "tier5wall"), ItemRegistry.tier5wall());
			tier5wall.shape(" x ","xyx"," x ");
			tier5wall.setIngredient('x', Material.DIAMOND);
			tier5wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier5wall);
		ShapedRecipe tier5frame = new ShapedRecipe(new NamespacedKey(plugin, "tier5frame"), ItemRegistry.tier5frame());
			tier5frame.shape(" x ", "xyx", " x ");
			tier5frame.setIngredient('x', Material.DIAMOND);
			tier5frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier5frame);
		ShapedRecipe tier5barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier5barrel"), ItemRegistry.tier5barrel());
			tier5barrel.shape("xxx", "xyx", "xxx");
			tier5barrel.setIngredient('x', ItemRegistry.tier5wall().getData());
			tier5barrel.setIngredient('y', ItemRegistry.tier5frame().getData());
			plugin.getServer().addRecipe(tier5barrel);
			
		ShapedRecipe tier6wall = new ShapedRecipe(new NamespacedKey(plugin, "tier6wall"), ItemRegistry.tier6wall());
			tier6wall.shape(" x ","xyx"," x ");
			tier6wall.setIngredient('x', Material.EMERALD);
			tier6wall.setIngredient('y', Material.SLIME_BALL);
			plugin.getServer().addRecipe(tier6wall);
		ShapedRecipe tier6frame = new ShapedRecipe(new NamespacedKey(plugin, "tier6frame"), ItemRegistry.tier6frame());
			tier6frame.shape(" x ", "xyx", " x ");
			tier6frame.setIngredient('x', Material.EMERALD);
			tier6frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier6frame);
		ShapedRecipe tier6barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier6barrel"), ItemRegistry.tier6barrel());
			tier6barrel.shape("xxx", "xyx", "xxx");
			tier6barrel.setIngredient('x', ItemRegistry.tier6wall().getData());
			tier6barrel.setIngredient('y', ItemRegistry.tier6frame().getData());
			plugin.getServer().addRecipe(tier6barrel);
			
		ShapedRecipe tier7wall = new ShapedRecipe(new NamespacedKey(plugin, "tier7wall"), ItemRegistry.tier7wall());
			tier7wall.shape(" x ","xyx"," x ");
			tier7wall.setIngredient('x', Material.END_STONE);
			tier7wall.setIngredient('y', Material.NETHER_STAR);
			plugin.getServer().addRecipe(tier7wall);
		ShapedRecipe tier7frame = new ShapedRecipe(new NamespacedKey(plugin, "tier7frame"), ItemRegistry.tier7frame());
			tier7frame.shape(" x ", "xyx", " x ");
			tier7frame.setIngredient('x', Material.END_STONE);
			tier7frame.setIngredient('y', Material.ITEM_FRAME);
			plugin.getServer().addRecipe(tier7frame);
		ShapedRecipe tier7barrel = new ShapedRecipe(new NamespacedKey(plugin, "tier7barrel"), ItemRegistry.tier7barrel());
			tier7barrel.shape("xxx", "xyx", "xxx");
			tier7barrel.setIngredient('x', ItemRegistry.tier7wall().getData());
			tier7barrel.setIngredient('y', ItemRegistry.tier7frame().getData());
			plugin.getServer().addRecipe(tier7barrel);
	}
	
}
