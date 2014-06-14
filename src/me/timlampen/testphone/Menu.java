package me.timlampen.testphone;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

 
public class Menu{

	private Inventory inv;
        private ItemStack a, b, c, d, e, f;
        public enum Inv{
        	Tier1BlackOut, Tier2BlackOut, Tier3BlackOut, Tier1GunJammer, Tier2GunJammer, Tier3GunJammer, 
    	}
        

    	public ItemStack getCustomItem(Inv inv) {
    		ItemStack is = null;
    		ItemMeta im;
    		ArrayList<String> Heart;
    		ArrayList<String> Ember;
    		ArrayList<String> Flame;
    		ArrayList<String> Music;
    		ArrayList<String> Magic;
    		switch (inv) {
    		case Tier1BlackOut:
    			is = new ItemStack(Material.SLIME_BALL);
    			im = is.getItemMeta();
    			Heart = new ArrayList<String>();
    			im.setDisplayName(ChatColor.DARK_BLUE + "Tier 1 BlackOut");
    			Heart.add(ChatColor.GRAY + "Click to cause chaos ");
    			im.setLore(Heart);
    			is.setItemMeta(im);
    			break;
    		case Tier2BlackOut:
    			is = new ItemStack(Material.ENDER_PEARL);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.DARK_PURPLE + "Tier 2 BlackOut");
    			Flame = new ArrayList<String>();
    			Flame.add(ChatColor.GRAY + "Click to cause chaos");
    			im.setLore(Flame);
    			is.setItemMeta(im);
    			break;
    		case Tier3BlackOut:
    			is = new ItemStack(Material.EYE_OF_ENDER);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.BLACK + "Tier 3 BlackOut");
    			Ember = new ArrayList<String>();
    			Ember.add(ChatColor.GRAY + "Click to cause chaos");
    			im.setLore(Ember);
    			is.setItemMeta(im);
    			break;
    		case Tier1GunJammer:
    			is = new ItemStack(Material.POISONOUS_POTATO);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.YELLOW + "Tier 1 Gun Jammer");
    			Music = new ArrayList<String>();
    			Music.add(ChatColor.GRAY + "Click to jam enemy guns in a 100x100 area");
    			im.setLore(Music);
    			is.setItemMeta(im);
    			break;
    		case Tier2GunJammer:
    			is = new ItemStack(Material.POTATO);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.GOLD + "Tier 2 Gun Jammer");
    			Magic = new ArrayList<String>();
    			Magic.add(ChatColor.GRAY + "Click to jam enemy guns in a 200x200 area");
    			im.setLore(Magic);
    			is.setItemMeta(im);
    			break;
    		case Tier3GunJammer:
    			is = new ItemStack(Material.BAKED_POTATO);
    			im = is.getItemMeta();
    			Heart = new ArrayList<String>();
    			im.setDisplayName(ChatColor.RED + "Tier 3 Gun Jammer");
    			Heart.add(ChatColor.GRAY + "Click to jam enemy guns in a 300x");
    			im.setLore(Heart);
    			is.setItemMeta(im);
    			break;
    		}
    		return is;
    	}
        public Menu(Plugin p) {
                inv = Bukkit.getServer().createInventory(null, 9, ChatColor.BLUE + "Stuff");
                a = getCustomItem(Inv.Tier1BlackOut);
                b = getCustomItem(Inv.Tier2BlackOut);
                c = getCustomItem(Inv.Tier3BlackOut);
                d = getCustomItem(Inv.Tier1GunJammer);
                e = getCustomItem(Inv.Tier2GunJammer);
                f = getCustomItem(Inv.Tier3GunJammer);
                inv.setItem(1, a);
                inv.setItem(2, b);
                inv.setItem(3, c);
                inv.setItem(5, d);
                inv.setItem(6, e);
                inv.setItem(7, f);       
        }
		
       
        public void show(Player player) {
                player.openInventory(inv);
        }
	
		public void doReplace(Player player, Location min, Location max, Material replace, Material with) {
		    for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
		    	player.sendMessage("x");
		        for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
			    	player.sendMessage("y");
		            for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
				    	player.sendMessage("z");
		                Block blk = min.getWorld().getBlockAt(new Location(min.getWorld(), x, y, z));
		                if(blk.getType()==replace){
		                    blk.setType(with);
		            }
		            }
		            }
		        }
		    }

		@SuppressWarnings("deprecation")
		public void doBlackOut(Location min, Location max) {
			for(Player all:Bukkit.getServer().getOnlinePlayers()){
			    for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
			        for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
			            for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
		            	if(all.getLocation().getBlockX()==x && all.getLocation().getBlockY()==y && all.getLocation().getBlockZ()==z){
        					all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150, 100));
        					all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 100));
        					all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 150, 100));
        					all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 2, 2);
        					all.playEffect(all.getLocation(), Effect.BLAZE_SHOOT, 2);
		            	}
		            }
		        }
		    }
		}
		}
}