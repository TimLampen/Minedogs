package me.timlampen.testphone;


import java.util.ArrayList;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

 
public class KitMenu implements Listener{

	private Inventory kitinv;
        private ItemStack a, b, c;
        public enum Kitinv{
        	Gunner, Launcher, Shotgun,
    	}
        

    	public ItemStack getCustomItem(Kitinv kitinv) {
    		ItemStack is = null;
    		ItemMeta im;
    		ArrayList<String> Heart;
    		ArrayList<String> Ember;
    		ArrayList<String> Flame;
    		switch (kitinv) {
    		case Gunner:
    			is = new ItemStack(Material.WOOD_PICKAXE);
    			im = is.getItemMeta();
    			Heart = new ArrayList<String>();
    			im.setDisplayName(ChatColor.GOLD + "Gunner Class");
    			Heart.add(ChatColor.GRAY + "Click to chose the gunner class ");
    			im.setLore(Heart);
    			is.setItemMeta(im);
    			break;
    		case Launcher:
    			is = new ItemStack(Material.WOOD_SWORD);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.GOLD + "Launcher Class");
    			Flame = new ArrayList<String>();
    			Flame.add(ChatColor.GRAY + "Click to chose the launcher class");
    			im.setLore(Flame);
    			is.setItemMeta(im);
    			break;
    		case Shotgun:
    			is = new ItemStack(Material.WOOD_SPADE);
    			im = is.getItemMeta();
    			im.setDisplayName(ChatColor.GOLD + "Shotgun Class");
    			Ember = new ArrayList<String>();
    			Ember.add(ChatColor.GRAY + "Click to chose the shotgun class");
    			im.setLore(Ember);
    			is.setItemMeta(im);
    			break;
    		}
    		return is;
    	}
        public KitMenu(Plugin p) {
                kitinv = Bukkit.getServer().createInventory(null, 9, ChatColor.BLUE + "Kit Selection");
                a = getCustomItem(Kitinv.Gunner);
                b = getCustomItem(Kitinv.Launcher);
                c = getCustomItem(Kitinv.Shotgun);
                kitinv.setItem(1, a);
                kitinv.setItem(4, b);
                kitinv.setItem(7, c);      
        }
		
       
        public void show(Player player) {
                player.openInventory(kitinv);
        }
    	@EventHandler
        public void onInventoryClick(final InventoryClickEvent event) {
        	final Player player = (Player) event.getWhoClicked();
        		if(event.getCurrentItem()!=null && event.getCurrentItem().getItemMeta()!=null && event.getCurrentItem().getItemMeta().getDisplayName()!=null && event.getCurrentItem().getType()!=null && event.getCurrentItem().getType()!=Material.AIR && event.getInventory().getName().contains("Kit Selection")){
        			if(event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Gunner Class")){
        				player.getInventory().clear();
        				player.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE, 1));
        				player.getInventory().addItem(new ItemStack(Material.TNT, 1));
    					player.closeInventory();
    	}
        			if(event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Launcher Class")){
        				player.getInventory().clear();
        				player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
        				player.getInventory().addItem(new ItemStack(Material.TNT, 2));
    					player.closeInventory();
    	}
        			if(event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Shotgun Class")){
        				player.getInventory().clear();
        				player.getInventory().addItem(new ItemStack(Material.WOOD_SPADE, 1));
        				player.getInventory().addItem(new ItemStack(Material.TNT, 1));
    					player.closeInventory();
    	}
    	}
        	}
        
}