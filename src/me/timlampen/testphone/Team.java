package me.timlampen.testphone;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import ca.wacos.nametagedit.NametagAPI;


public class Team implements Listener{
	public HashSet<String> blueTeam = new HashSet<String>();
	public HashSet<String> redTeam = new HashSet<String>();
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			if(event.getDamager() instanceof Egg){
			Player defender = (Player) event.getEntity();
		      Egg e = (Egg)event.getDamager();
			Player attacker = (Player) e.getShooter();
			if((blueTeam.contains(defender) && blueTeam.contains(attacker)) || (redTeam.contains(defender) && redTeam.contains(attacker))){
				event.setCancelled(true);
				attacker.sendMessage("Friendly Fire Is Off!!!");
				attacker.setHealth(attacker.getHealth()-1);
			}
			else if((redTeam.contains(defender) && blueTeam.contains(attacker)) || (blueTeam.contains(defender) && redTeam.contains(attacker))){
		        if (attacker.getItemInHand().getType() == Material.WOOD_PICKAXE) {
			          event.setDamage(5.0D);
	        	attacker.playEffect(attacker.getLocation(), Effect.MOBSPAWNER_FLAMES, 2004);
		        }
			}
	        else{
	        	event.setDamage(5.0D);
	        }
			}
		if(event.getDamager() instanceof Arrow){
			Player defender = (Player) event.getEntity();
		      Arrow a = (Arrow)event.getDamager();
			Player attacker = (Player) a.getShooter();
			if((blueTeam.contains(defender) && blueTeam.contains(attacker)) || (redTeam.contains(defender) && redTeam.contains(attacker))){
				event.setCancelled(true);
				attacker.sendMessage("Friendly Fire Is Off!!!");
				attacker.setHealth(attacker.getHealth()-1);
			}
			else if((redTeam.contains(defender) && blueTeam.contains(attacker)) || (blueTeam.contains(defender) && redTeam.contains(attacker))){
		        if (attacker.getItemInHand().getType() == Material.WOOD_PICKAXE) {
			          event.setDamage(5.0D);
	        	attacker.playEffect(attacker.getLocation(), Effect.MOBSPAWNER_FLAMES, 2004);
		        }
			}
	        else{
	        	event.setDamage(5.0D);
	        }
			
		}
		if(event.getDamager() instanceof Fireball){
			Player defender = (Player) event.getEntity();
		      Fireball f = (Fireball)event.getDamager();
			Player attacker = (Player) f.getShooter();
			if((blueTeam.contains(defender) && blueTeam.contains(attacker)) || (redTeam.contains(defender) && redTeam.contains(attacker))){
				event.setCancelled(true);
				attacker.sendMessage("Friendly Fire Is Off!!!");
				attacker.setHealth(attacker.getHealth()-1);
			}
			else if((redTeam.contains(defender) && blueTeam.contains(attacker)) || (blueTeam.contains(defender) && redTeam.contains(attacker))){
		        if (attacker.getItemInHand().getType() == Material.WOOD_PICKAXE) {
			          event.setDamage(5.0D);
	        	attacker.playEffect(attacker.getLocation(), Effect.MOBSPAWNER_FLAMES, 2004);
		        }
		        
			}
	        else{
	        	event.setDamage(5.0D);
	        }
			
		}
		if(event.getCause() == DamageCause.FALL){
			event.setCancelled(true);
		}
	}
	}

}
