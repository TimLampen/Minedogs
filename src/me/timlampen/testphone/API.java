package me.timlampen.testphone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.wacos.nametagedit.NametagAPI;

public class API implements Listener{
	Menu menu;
	Team team;
	int playercount;
	boolean started;
	private TestPhone plugin;
	  public API(TestPhone plugin){
		    this.plugin = plugin;
		  }
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		addTeam(player);
		playercount++;
		gameStart();
	}
	public void gameStart(){
		if(playercount<10){
			Bukkit.broadcastMessage(ChatColor.BLUE + "A player has joined! " + playercount + "/10 have joined!");
		}
		if(playercount>=10){
			playercount = 0;
			for(Player allplayers:Bukkit.getServer().getOnlinePlayers()){
				allplayers.teleport(new Location(Bukkit.getWorld("Watchdogs"), 0, 200, 0));
				BarAPI.setMessage(allplayers, ChatColor.BLUE + "Complete The Telephone To Win The Game!", 600);
				allplayers.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600000, 2));
			}
		}
	}
	 @EventHandler
	 public void onEntityExplode(EntityExplodeEvent e) {
	 ArrayList<Block> blocks = new ArrayList<>();
	 ArrayList<Block> dontexplode = new ArrayList<>();
	 for (Block b : e.blockList()) {
	 if (b.getType() == Material.WOOL || b.getType() == Material.TORCH) {
	 blocks.add(b);
	 } else {
	 dontexplode.add(b);
	 }
	 }
	 for(Block b : dontexplode){
	 e.blockList().remove(b);
	 b.getState().update(true);
	 }e.setYield(0);
	 GeschenkExplosion ge = new GeschenkExplosion(blocks);
	 Bukkit.getScheduler().runTaskLater(this.plugin, ge, 20 * 20);
	 }
		public HashSet<String> blueTeam = new HashSet<String>();
		public HashSet<String> redTeam = new HashSet<String>();
		public void addTeam(Player player){
			if(redTeam.size() > blueTeam.size()){
				blueTeam.add(player.getName());
				player.sendMessage(ChatColor.BLUE + "You have joined blue team!");
				NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.BLUE + "Blue" + ChatColor.YELLOW + "] ");
			}
			if(redTeam.size() < blueTeam.size()){
				redTeam.add(player.getName());
				player.sendMessage(ChatColor.RED + "You have joined red team!");
				NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.RED + "Red" + ChatColor.YELLOW + "] ");
			}
	        else
	        {
	            Integer team = new Random().nextInt(2);
	            if (team == 1)
	            {
	                redTeam.add(player.getName());
	    			player.sendMessage(ChatColor.BLUE + "You have joined blue team!");
	    			NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.BLUE + "Blue" + ChatColor.YELLOW + "] ");
	            }
	            else
	            {
	                blueTeam.add(player.getName());
	    			player.sendMessage(ChatColor.RED + "You have joined red team!");
	    			NametagAPI.setPrefix(player.getName(), ChatColor.YELLOW + "[" + ChatColor.RED + "Red" + ChatColor.YELLOW + "] ");
	            }
	        }
		}
	 }
