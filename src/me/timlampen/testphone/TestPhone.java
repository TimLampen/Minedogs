package me.timlampen.testphone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.wacos.nametagedit.NametagAPI;
public class TestPhone extends JavaPlugin implements Listener{
	public static TestPhone plugin;
	Menu menu;
	KitMenu kitmenu;
	API api;
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new API(this), this);
		pm.registerEvents(new Guns(this), this);
		pm.registerEvents(new Team(), this);
		pm.registerEvents(new KitMenu(this), this);
		pm.registerEvents(this, this);
		this.soda();
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		ItemStack is = player.getItemInHand();
			if(is.getType()!=Material.AIR && is!=null && is!=new ItemStack(Material.AIR) && is.getType()!=null){
					 if(is.getType()==Material.MAGMA_CREAM){
						 menu = new Menu(this);
							 menu.show(event.getPlayer());
		}
					 if(is.getType()==Material.BLAZE_POWDER){
						 kitmenu = new KitMenu(this);
						 kitmenu.show(event.getPlayer());
					 }
		}
		}
 
	public void soda() {
        ShapelessRecipe recipe = new ShapelessRecipe(getCustomItem(Items.Phone));
        recipe.addIngredient(Material.INK_SACK);
        recipe.addIngredient(Material.GREEN_RECORD);
        recipe.addIngredient(Material.INK_SACK:1);
        recipe.addIngredient(Material.INK_SACK:15);
        this.getServer().addRecipe(recipe);
    }
	public enum Items {
		Phone,
	}

	public ItemStack getCustomItem(Items item) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> Soda;
		switch (item) {
		case Phone:
			is = new ItemStack(Material.MAGMA_CREAM);
			im = is.getItemMeta();
			im.setDisplayName(ChatColor.BLUE + "Telephone");
			Soda = new ArrayList<String>();
			Soda.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Magical");
			im.setLore(Soda);
			is.setItemMeta(im);
			break;
		}
		return is;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
    	final Player player = (Player) event.getWhoClicked();
    		if(event.getCurrentItem()!=null && event.getCurrentItem().getItemMeta()!=null && event.getCurrentItem().getItemMeta().getDisplayName()!=null && event.getCurrentItem().getType()!=null && event.getCurrentItem().getType()!=Material.AIR){
    			if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier 1 BlackOut")){
					player.closeInventory();
    				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.REDSTONE_LAMP_ON, Material.COAL_BLOCK);
    				Bukkit.getServer().broadcastMessage(player.getDisplayName() + "" + ChatColor.DARK_BLUE + " Has Activate A T1 BlackOut!");
    				effectst1();
    				player.getInventory().remove(new ItemStack(Material.MAGMA_CREAM, 1));
    				player.updateInventory();
    				Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable(){

						@Override
						public void run() {
							Bukkit.broadcastMessage("The Lights have been repaired!");
	        				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.COAL_BLOCK, Material.REDSTONE_LAMP_ON);
							
						}}, 1200);
	}
    			if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier 2 BlackOut")){
					player.closeInventory();
    				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.REDSTONE_LAMP_ON, Material.COAL_BLOCK);
    				Bukkit.getServer().broadcastMessage(player.getDisplayName() + "" + ChatColor.DARK_PURPLE + " Has Activate A T2 BlackOut!");
    				effectst2();
    				player.getInventory().remove(new ItemStack(Material.MAGMA_CREAM, 1));
    				player.updateInventory();
    				Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable(){

						@Override
						public void run() {
							Bukkit.broadcastMessage("The Lights have been repaired!");
	        				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.COAL_BLOCK, Material.REDSTONE_LAMP_ON);
							
						}}, 6000);
	}
    			if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier 3 BlackOut")){
					player.closeInventory();
    				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.REDSTONE_LAMP_ON, Material.COAL_BLOCK);
    				Bukkit.getServer().broadcastMessage(player.getDisplayName() + "" + ChatColor.BLACK + " Has Activate A T3 BlackOut!");
    				effectst3();
    				player.getInventory().remove(new ItemStack(Material.MAGMA_CREAM, 1));
    				player.updateInventory();
    				Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable(){

						@Override
						public void run() {
							Bukkit.broadcastMessage("The Lights have been repaired!");
	        				doReplace(player, (new Location(player.getWorld(),-3,65,-14)), (new Location(player.getWorld(),6,68,5)), Material.COAL_BLOCK, Material.REDSTONE_LAMP_ON);
							
						}}, 1200);
	}
	}
    	}
	public void doReplace(Player player, Location min, Location max, Material replace, Material with) {
	    for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
	        for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
	            for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
	                Block blk = min.getWorld().getBlockAt(new Location(min.getWorld(), x, y, z));
	                if(blk.getType()==replace){
	                    blk.setType(with);
	            }
	            }
	            }
	        }
	    }
	
	public void effectst1(){
		for(Player all: Bukkit.getOnlinePlayers()){
			all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 3, 3);
			all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 100));
	}
	}
	public void effectst2(){
		for(Player all: Bukkit.getOnlinePlayers()){
			all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 3, 3);
			all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 100));
		}
	}
	public void effectst3(){
		for(Player all: Bukkit.getOnlinePlayers()){
			all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 3, 3);
			all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 500, 100));
			all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 100));
		}
	}
   
	}

