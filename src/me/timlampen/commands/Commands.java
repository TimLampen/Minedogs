package me.timlampen.commands;



import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import me.timlampen.honornetwork.HonorNetwork.Items;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;


public class Commands extends JavaPlugin implements Listener{
	  public enum Mobs{
		  Zombie;
	  }
	  public ItemStack getCustomItem(Mobs mob){
		  ItemStack is = null;
		  ItemMeta im;
		  ArrayList<String> lore;
		  switch (mob){
		  case Zombie:
		  }
		return is;
	  }

		@Override
		public void onEnable(){
	    	PluginManager pm = getServer().getPluginManager();
	        pm.registerEvents(this, this);
		}
		@SuppressWarnings("deprecation")
		public boolean onCommand(CommandSender sender, Command cmd,String commandLabel, String[] args){
			if(commandLabel.equalsIgnoreCase("npc")){
				if(args.length>=1){
				if(args[1].equalsIgnoreCase("spawn")){
					Player player = (Player) sender;
					Location location = player.getLocation();
					player.getWorld().spawnCreature(location, EntityType.ZOMBIE);
				}
			}
			}
			return false;
		}
		@EventHandler
		public void onSpawn(CreatureSpawnEvent event){
			LivingEntity entity = (LivingEntity) event.getEntity();
	        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, -1, -1));
		}
		@EventHandler
		public void onWhatever(PlayerInteractEntityEvent event){
			event.getRightClicked().get
			  
		    }
		}
}