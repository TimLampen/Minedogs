package com.minetendo.minesurvival;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MineSurvival
  extends JavaPlugin
  implements Listener
{
private HashMap<String, Long> lastUsage = new HashMap();
  private HashMap<String, Integer> daysMap = new HashMap();
  private HashMap<String, Long> damage = new HashMap();
  private HashMap<String, Boolean> killable = new HashMap();
  private final int cdtime = 10;
  private final int damagetime = 10;
  
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
    int days = ((Integer)this.daysMap.get(player.getName())).intValue();
    PermissionUser user = PermissionsEx.getUser(player);
    if (commandLabel.equalsIgnoreCase("days")) {
      if ((player.hasPermission("minesurvival.days")) || (player.hasPermission("minesurvival.hcdays")))
      {
        long lastUsed = 0L;
        if (this.lastUsage.containsKey(player.getName())) {
          lastUsed = ((Long)this.lastUsage.get(player.getName())).longValue();
        }
        long cdmillis = System.currentTimeMillis() + 10000L;
        if (System.currentTimeMillis() - lastUsed >= cdmillis)
        {
          if (this.daysMap.containsKey(player.getName()))
          {
            days++;
            this.daysMap.put(player.getName(), Integer.valueOf(days));
            player.sendMessage(ChatColor.RED + "You have stayed alive for " + ChatColor.BLUE + days + ChatColor.RED + " days!");
            getServer().dispatchCommand(getServer().getConsoleSender(), "pex user " + player.getName() + " prefix &c[&b" + days + "&c]");
            this.lastUsage.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
          }
          else if (this.daysMap.containsKey(player.getName()))
          {
            if ((days == 10) || (days == 99))
            {
              days++;
              this.daysMap.put(player.getName(), Integer.valueOf(days));
              Bukkit.broadcastMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.RED + "Has survived" + ChatColor.BLUE + days + ChatColor.RED + "!");
              this.lastUsage.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
            }
          }
          else if (!this.daysMap.containsKey(player.getName()))
          {
            this.daysMap.put(player.getName(), Integer.valueOf(0));
          }
        }
        else
        {
          int timeLeft = (int)(10L - (System.currentTimeMillis() - lastUsed) / 1000L);
          player.sendMessage(ChatColor.RED + "It hasn't been a day yet " + ChatColor.GREEN + timeLeft + ChatColor.RED + " seconds left");
          return true;
        }
      }
      else if (!player.hasPermission("minesurvival.days"))
      {
        player.sendMessage(ChatColor.RED + "No permission");
      }
    }
    if (commandLabel.equalsIgnoreCase("hardcore")) {
      if (!player.hasPermission("minesurvival.normal"))
      {
        user.addPermission("minesurvival.hardcore");
        player.sendMessage(ChatColor.RED + "You are now playing hardcore");
      }
      else if (player.hasPermission("minesurvival.normal"))
      {
        player.sendMessage(ChatColor.RED + "You cannot switch from normal to hardcore!");
      }
    }
    if (commandLabel.equalsIgnoreCase("normal")) {
      if (!player.hasPermission("minesurvival.hardcore"))
      {
        user.addPermission("minesurvival.normal");
        player.sendMessage(ChatColor.RED + "You are now playing normal");
      }
      else if (player.hasPermission("minesurvival.hardcore"))
      {
        player.sendMessage(ChatColor.RED + "You cannot switch from hardcore to normal!");
      }
    }
    if ((commandLabel.equalsIgnoreCase("timeleft")) && 
      (this.damage.containsKey(player.getName()))) {
      this.damage.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
    }
    return false;
  }
  
  @EventHandler
  public void onPlayerDeathEvent(PlayerDeathEvent event)
  {
    Player player = event.getEntity();
    PermissionUser user = PermissionsEx.getUser(player);
    if ((player instanceof Player))
    {
      int daysdeath = ((Integer)this.daysMap.get(player.getName())).intValue();
      if (player.hasPermission("minesurvival.normal"))
      {
        int death = Math.round(daysdeath - daysdeath * 20 / 100);
        this.daysMap.put(player.getName(), Integer.valueOf(death));
        user.removePermission("minesurvival.normal");
        player.sendMessage(ChatColor.RED + "Because you were playing normal, your days have been reset to " + ChatColor.GREEN + death);
      }
      else if (player.hasPermission("minesurvival.hardcore"))
      {
        int hcdeath = Math.round(daysdeath - daysdeath * 80 / 100);
        this.daysMap.put(player.getName(), Integer.valueOf(hcdeath));
        user.removePermission("minesurvival.hardcore");
        player.sendMessage(ChatColor.RED + "Because you were playing hardcore, your days have been reset to " + ChatColor.GREEN + hcdeath);
      }
      else {}
    }
  }
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event)
  {
    ItemStack is = event.getItem();
    Player player = event.getPlayer();
    long dmgmillis = System.currentTimeMillis() + 10000L;
    long DamageUsed = 0L;
    if (is.getType() == Material.NETHER_STAR)
    {
      this.damage.put(player.getName(), Long.valueOf(1L));
      player.getInventory().remove(Material.NETHER_STAR);
      player.sendMessage(ChatColor.GREEN + "You have activated your 10 second powerup!");
      DamageUsed = ((Long)this.damage.get(player.getName())).longValue();
      if (System.currentTimeMillis() - DamageUsed >= dmgmillis)
      {
        player.sendMessage("3");
        this.killable.put(player.getName(), Boolean.valueOf(true));
      }
    }
    else {}
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    Player player = event.getPlayer();
    this.killable.put(player.getName(), Boolean.valueOf(false));
  }
  
  @EventHandler(priority=EventPriority.NORMAL)
  public void dmg(EntityDamageEvent event)
  {
    Entity e = event.getEntity();
    if ((event instanceof EntityDamageByEntityEvent))
    {
      if ((e instanceof Player))
      {
        Player player = (Player)e;
        if ((this.killable.containsKey(player.getName())) && 
          (((Boolean)this.killable.get(player)).booleanValue())) {
          event.setCancelled(true);
        }
      }
    }
    else {}
  }
}
