package com.minetendo.minetendosurvival;

import java.util.HashMap;

import org.bukkit.entity.Player;
 
public class Cooldown {
 
    public MineSurvival p;
 
    public Cooldown(MineSurvival i) {
        p = i;
    }
 
    int task;
    HashMap<String,Long> cooldowns = new HashMap<String,Long>();
    final int seconds = 60;
 
    public long getTimeLeft(Player player, HashMap<String, Integer> hashmap) {
        long time = cooldowns.get(player.getUniqueId().toString()) + (20 * 60 * 1000) - System.currentTimeMillis();
        return time;
    }
 
    public boolean hasCooldown(Player player){
    	if(cooldowns.get(player.getUniqueId().toString()) + (20 * 60 * 1000) >= System.currentTimeMillis()){
    	return false;
    	}
    	else{
    	return true;
    	}
    	}
    
    public void activateCooldown(Player player){
    	cooldowns.put(player.getUniqueId().toString(), System.currentTimeMillis());
    	}
    	 
 
}