package com.minetendo.minetendosurvival;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MineSurvival extends JavaPlugin implements Listener{
  private HashMap<String, Integer> daysMap = new HashMap<String, Integer>();
  private HashMap<String, Long> timeh = new HashMap<String, Long>();
  private HashMap<String, Boolean> killable = new HashMap<String, Boolean>();
  HashMap<String, Integer> map = new HashMap<String, Integer>();
  Cooldown cd;
  
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    for (String str : getConfig().getKeys(true))
    {
      int p = getConfig().getInt(str);
      this.daysMap.put(str, Integer.valueOf(p));
    }
    saveConfig();
  }
  
  public void onDisable()
  {
    for (Map.Entry<String, Integer> pointstostore : this.daysMap.entrySet()) {
      getConfig().set((String)pointstostore.getKey(), pointstostore.getValue());
    }
    saveConfig();
  }
  
  @EventHandler 
  public void onCommandProcess(PlayerCommandPreprocessEvent event){
	  Player player = event.getPlayer();
	  PermissionUser user = PermissionsEx.getUser(player);
	  int days = daysMap.put(player.getUniqueId().toString(), (daysMap.get(player.getUniqueId().toString())+1));
	  if(cd.hasCooldown(player)){
		  return;
  }
	  if(!cd.cooldowns.containsKey(player.getUniqueId().toString())){
		  cd.activateCooldown(player);
	  }
	  else{
		  days++;
		  player.sendMessage("You Lived For Day!");
		  user.setPrefix(ChatColor.GREEN+"["+ChatColor.BLUE+""+ChatColor.BOLD+days+ChatColor.RESET+""+ChatColor.GREEN+"]", "world");
		  cd.activateCooldown(player);
	  }
  }
  }