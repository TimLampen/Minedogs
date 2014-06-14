package me.timlampen.testphone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


public class Guns
  implements Listener
{
  public HashMap<String, Integer> numberbazooka = new HashMap<>();
  public HashMap<String, Integer> numberak47 = new HashMap<>();
  public HashMap<String, Integer> numbershotgun = new HashMap<>();
  public HashSet<String> reloadak47 = new HashSet<>();
  public HashSet<String> reloadshotgun = new HashSet<>();
  public HashSet<String> reloadbazooka = new HashSet<>();
  private TestPhone plugin;
  public int ak47;
  public int bazooka;
  public int shotgun;
  
  public Guns(TestPhone plugin){
    this.plugin = plugin;
  }
 
  
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event){
    short ak47minus = 5;
    short bazookaminus = 20;
    short shotgunminus = 30;
    final Player player = event.getPlayer();
	if(event.getItem()!=null && event.getItem().getType()!=Material.AIR && event.getItem().getType()!=null){
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))) {
    	if(event.getItem().getType() == Material.WOOD_PICKAXE){

      if (player.getInventory().getItemInHand().getDurability() >= 59){
          BarAPI.setMessage(player, ChatColor.GOLD + "You have ran out of ammo! Left-Click to reload!", 100f);
      }
      else if (!reloadak47.contains(player.getUniqueId().toString())){
        player.getInventory().getItemInHand().setDurability((short)(player.getInventory().getItemInHand().getDurability() + ak47minus));
        Egg a = (Egg)event.getPlayer().launchProjectile(Egg.class);
        a.setShooter(player);
      }
      else {
          BarAPI.setMessage(player, ChatColor.GOLD + "You cannot shoot while reloading!", 100f);
      }
    }
    	if(event.getItem().getType() == Material.WOOD_SWORD){
    	      if (player.getInventory().getItemInHand().getDurability() >= 58){
    	          BarAPI.setMessage(player, ChatColor.GOLD + "You have ran out of ammo! Left-Click to reload!", 100f);
    	      }
    	      else if (!reloadbazooka.contains(player.getUniqueId().toString())){
    	    	player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
    	        player.getInventory().getItemInHand().setDurability((short)(player.getInventory().getItemInHand().getDurability() + bazookaminus));
    	        Fireball e = (Fireball)event.getPlayer().launchProjectile(Fireball.class);
    	        e.setIsIncendiary(false);
    	        e.setShooter(player);
    	      }
    	      else {
    	          BarAPI.setMessage(player, ChatColor.GOLD + "You cannot shoot while reloading!", 100f);
    	      }
    	    }
    	if(event.getItem().getType() == Material.WOOD_SPADE){

    	      if (player.getInventory().getItemInHand().getDurability() >= 59){
    	          BarAPI.setMessage(player, ChatColor.GOLD + "You have ran out of ammo! Left-Click to reload!", 100f);
    	      }
    	      else if (!reloadshotgun.contains(player.getUniqueId().toString())){
    	        player.getInventory().getItemInHand().setDurability((short)(player.getInventory().getItemInHand().getDurability() + shotgunminus));
    	        Arrow a = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
    	        Arrow b = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
    	        Arrow c = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
    	        Arrow d = (Arrow)event.getPlayer().launchProjectile(Arrow.class);
    	        a.setShooter(player);
    	        b.setShooter(player);
    	        c.setShooter(player);
    	        d.setShooter(player);
    	      }
    	      else {
    	          BarAPI.setMessage(player, ChatColor.GOLD + "You cannot shoot while reloading!", 100f);
    	      }
    	    }
    	if(event.getItem().getType() == Material.STICK){
  	        player.getInventory().getItemInHand().setDurability((short)(player.getInventory().getItemInHand().getDurability() + bazookaminus));
  	        Fireball e = (Fireball)event.getPlayer().launchProjectile(Fireball.class);
  	        e.setPassenger(player);
  	        e.setIsIncendiary(false);
  	        e.setShooter(player);
  	      }
        if (event.getItem().getType() == Material.TNT) {
            event.setCancelled(true);
            event.setUseItemInHand(Result.DENY);
            Entity tnt = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation().add(0, 1, 0), TNTPrimed.class);
            ((TNTPrimed)tnt).setFuseTicks(20);
            tnt.setVelocity(event.getPlayer().getLocation().getDirection());
            player.getInventory().remove(new ItemStack(Material.TNT, 1));

    }
    }
    }
    if (((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK))) {
    	if(event.getItem()!=null && event.getItem().getType()!=Material.AIR && event.getItem().getType()!=null){
    	if(event.getItem().getType() == Material.WOOD_PICKAXE){
      if (!reloadak47.contains(player.getUniqueId().toString())) {
        numberbazooka.remove(player.getUniqueId().toString());        
        numberak47.put(player.getUniqueId().toString(), 1);
        final int test = player.getInventory().getHeldItemSlot();
        

        ak47 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable(){
		public void run(){
        	  if(numberak47.containsKey(player.getUniqueId().toString())){
            int iak47 = (numberak47.get(player.getUniqueId().toString()));
            if (numberak47.containsKey(player.getUniqueId().toString())) {
            	if(test != player.getInventory().getHeldItemSlot()){
            		BarAPI.setMessage(player, ChatColor.GOLD + "You have cancelled reloading your weapon!");
                    numberak47.remove(player.getUniqueId().toString());
                    reloadak47.remove(player.getUniqueId().toString());
            		Bukkit.getScheduler().cancelTask(ak47);
            	}
            	else{
              if ((numberak47.get(player.getUniqueId().toString())) == 15) {
                BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.DARK_RED + "25%"  + ChatColor.DARK_AQUA + "»", 25f);
                numberak47.put(player.getUniqueId().toString(), iak47 + 1);
                player.getItemInHand().setDurability((short)(59 - iak47));
              }
              if ((numberak47.get(player.getUniqueId().toString())) == 30) {
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.RED + "50%"  + ChatColor.DARK_AQUA + "»", 50f);
                numberak47.put(player.getUniqueId().toString(), iak47 + 1);
                player.getItemInHand().setDurability((short)(59 - iak47));
              }
              if ((numberak47.get(player.getUniqueId().toString())) == 45) {
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.YELLOW + "75%"  + ChatColor.DARK_AQUA + "»", 75f);
                numberak47.put(player.getUniqueId().toString(), iak47 + 1);
                player.getItemInHand().setDurability((short)(59 - iak47));
              }
              if ((numberak47.get(player.getUniqueId().toString())) >= 59){
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloaded!" + ChatColor.DARK_AQUA + " «" + ChatColor.GREEN + "100%"  + ChatColor.DARK_AQUA + "»", 100f);
                numberak47.remove(player.getUniqueId().toString());
                reloadak47.remove(player.getUniqueId().toString());
                Bukkit.getScheduler().cancelTask(ak47);
              }
              else{
                numberak47.put(player.getUniqueId().toString(), iak47 + 1);
                player.getItemInHand().setDurability((short)(59 - iak47));
                reloadak47.add(player.getUniqueId().toString());
              }
            }
          }
        	  }
          }
        }, 20L, 2L);
      }
      else{
          BarAPI.setMessage(player, ChatColor.GOLD + "You are already reloading!", 100f);
      }
    		}
    	if(event.getItem().getType() == Material.WOOD_SWORD){
    	      if (!reloadbazooka.contains(player.getUniqueId().toString())) {
    	        numberak47.remove(player.getUniqueId().toString());
    	        numberbazooka.put(player.getUniqueId().toString(), 1);
    	        final int test = player.getInventory().getHeldItemSlot();
    	        

    	        bazooka = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable(){
    	          public void run(){
    	        	  if(numberbazooka.containsKey(player.getUniqueId().toString())){
    	            int ibazooka = (numberbazooka.get(player.getUniqueId().toString()));
    	            if (numberbazooka.containsKey(player.getUniqueId().toString())) {
    	            	if(test != player.getInventory().getHeldItemSlot()){
    	            		BarAPI.setMessage(player, ChatColor.GOLD + "You have cancelled reloading your weapon!");
    	                    numberak47.remove(player.getUniqueId().toString());
    	                    reloadak47.remove(player.getUniqueId().toString());
    	            		Bukkit.getScheduler().cancelTask(ak47);
    	            	}
    	              if ((numberbazooka.get(player.getUniqueId().toString())) == 15) {
    	                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.DARK_RED + "25%"  + ChatColor.DARK_AQUA + "»", 25f);
    	                numberbazooka.put(player.getUniqueId().toString(), ibazooka + 1);
    	                player.getItemInHand().setDurability((short)(59 - ibazooka));
    	              }
    	              if ((numberbazooka.get(player.getUniqueId().toString())) == 30) {
    	                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.RED + "50%"  + ChatColor.DARK_AQUA + "»", 50f);
    	                numberbazooka.put(player.getUniqueId().toString(), ibazooka + 1);
    	                player.getItemInHand().setDurability((short)(59 - ibazooka));
    	              }
    	              if ((numberbazooka.get(player.getUniqueId().toString())) == 45) {
    	                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.YELLOW + "75%"  + ChatColor.DARK_AQUA + "»", 75f);
    	                numberbazooka.put(player.getUniqueId().toString(), ibazooka + 1);
    	                player.getItemInHand().setDurability((short)(59 - ibazooka));
    	              }
    	              if ((numberbazooka.get(player.getUniqueId().toString())) >= 59){
    	                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloaded!" + ChatColor.DARK_AQUA + " «" + ChatColor.GREEN + "100%"  + ChatColor.DARK_AQUA + "»", 100f);
    	                numberbazooka.remove(player.getUniqueId().toString());
    	                reloadbazooka.remove(player.getUniqueId().toString());
    	                Bukkit.getScheduler().cancelTask(bazooka);
    	              }
    	              else{
    	                numberbazooka.put(player.getUniqueId().toString(), ibazooka + 1);
    	                player.getItemInHand().setDurability((short)(59 - ibazooka));
    	                reloadbazooka.add(player.getUniqueId().toString());
    	              }
    	            }
    	          }
    	          }
    	        }, 20L, 2L);
    	      }
    	      else{
                  BarAPI.setMessage(player, ChatColor.GOLD + "You are already reloading!", 100f);
    	      }
    	    }
    	if(event.getItem().getType() == Material.WOOD_SPADE){
    		if(event.getItem().getType()!=Material.AIR){
      if (!reloadshotgun.contains(player.getUniqueId().toString())) {
        numberbazooka.remove(player.getUniqueId().toString());        
        numbershotgun.put(player.getUniqueId().toString(), 1);
        final int test = player.getInventory().getHeldItemSlot();
        

        shotgun = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable(){
		public void run(){
        	  if(numbershotgun.containsKey(player.getUniqueId().toString())){
            int ishotgun = (numbershotgun.get(player.getUniqueId().toString()));
            if (numbershotgun.containsKey(player.getUniqueId().toString())) {
            	if(test != player.getInventory().getHeldItemSlot()){
            		BarAPI.setMessage(player, ChatColor.GOLD + "You have cancelled reloading your weapon!");
                    numbershotgun.remove(player.getUniqueId().toString());
                    reloadshotgun.remove(player.getUniqueId().toString());
            		Bukkit.getScheduler().cancelTask(shotgun);
            	}
            	else{
              if ((numbershotgun.get(player.getUniqueId().toString())) == 15) {
                BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.DARK_RED + "25%"  + ChatColor.DARK_AQUA + "»", 25f);
                numbershotgun.put(player.getUniqueId().toString(), ishotgun + 1);
                player.getItemInHand().setDurability((short)(59 - ishotgun));
              }
              if ((numbershotgun.get(player.getUniqueId().toString())) == 30) {
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.RED + "50%"  + ChatColor.DARK_AQUA + "»", 50f);
                numberak47.put(player.getUniqueId().toString(), ishotgun + 1);
                player.getItemInHand().setDurability((short)(59 - ishotgun));
              }
              if ((numbershotgun.get(player.getUniqueId().toString())) == 45) {
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloading ->" + ChatColor.DARK_AQUA + " «" + ChatColor.YELLOW + "75%"  + ChatColor.DARK_AQUA + "»", 75f);
                numbershotgun.put(player.getUniqueId().toString(), ishotgun + 1);
                player.getItemInHand().setDurability((short)(59 - ishotgun));
              }
              if ((numbershotgun.get(player.getUniqueId().toString())) >= 59){
                  BarAPI.setMessage(player, ChatColor.GOLD + "Weapon Reloaded!" + ChatColor.DARK_AQUA + " «" + ChatColor.GREEN + "100%"  + ChatColor.DARK_AQUA + "»", 100f);
                numbershotgun.remove(player.getUniqueId().toString());
                reloadshotgun.remove(player.getUniqueId().toString());
                Bukkit.getScheduler().cancelTask(shotgun);
              }
              else{
                numbershotgun.put(player.getUniqueId().toString(), ishotgun + 1);
                player.getItemInHand().setDurability((short)(59 - ishotgun));
                reloadshotgun.add(player.getUniqueId().toString());
              }
            }
          }
        	  }
          }
        }, 20L, 2L);
      }
      else{
          BarAPI.setMessage(player, ChatColor.GOLD + "You are already reloading!", 100f);
      }
    		}
    }
    	}
  }
  }
  
  
  @EventHandler
  public void onEggThrow(PlayerEggThrowEvent event){
    event.setHatching(false);
  }
  
}
