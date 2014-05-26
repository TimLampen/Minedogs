package com.minetendo.minesurvival;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MineSurvival extends JavaPlugin implements Listener{
  private HashMap<String, Integer> daysMap = new HashMap<String, Integer>();
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
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player player = (Player)sender;
    PermissionUser user = PermissionsEx.getUser(player);
	return false;
  }
  
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    Player player = event.getPlayer();
  }
  
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event){
	  Player player = event.getPlayer();
	  if(cd.cooldowns.containsKey(player.getUniqueId().toString())){
          if (cd.hasCooldown(player)){
        	  player.sendMessage("Hasn't been a day yet bb");
          }
          else if(!cd.hasCooldown(player)){
            player.sendMessage(ChatColor.RED + "You have stayed alive for days!");
      }
	  }
          else{
        	  cd.activateCooldown(player);
        	  player.sendMessage("Hai");
          }
  }
  }
