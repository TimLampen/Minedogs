package com.minetendo.minesurvival;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
 
public class Cooldown {
 
    public MineSurvival p;
 
    public Cooldown(MineSurvival i) {
        p = i;
    }
 
    int task;
    HashMap<String,Long> cooldowns = new HashMap<String,Long>();
    final int seconds = 60;
 
    public int getTimeLeft(Player player, HashMap<String, Integer> hashmap) {
        int time = hashmap.get(player.getName());
        return time;
    }
 
    public boolean hasCooldown(Player player){
    	if(cooldowns.get(player.getUniqueId().toString()) + (20 * 60 * 1000) >= System.currentTimeMillis())
    	return false;
    	else
    	return true;
    	}
    
    public void activateCooldown(Player player){
    	cooldowns.put(player.getUniqueId().toString(), System.currentTimeMillis());
    	}
    	 
 
}