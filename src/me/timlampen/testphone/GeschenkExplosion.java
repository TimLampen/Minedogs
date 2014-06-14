package me.timlampen.testphone;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;
 
public class GeschenkExplosion extends BukkitRunnable{
 
    List<BlockState> states;
	private TestPhone plugin;
	  public GeschenkExplosion(TestPhone plugin){
		    this.plugin = plugin;
		  }
    public GeschenkExplosion(ArrayList<BlockState> blocks){
        states = blocks;
    }
 
    public GeschenkExplosion(List<Block> blocks){
        states = new ArrayList<>();
        for(Block b : blocks){
            states.add(b.getState());
        }
    }
 
    @Override
    public void run() {
        regen();
    }
 
    public void regen(){
    	
        for(final BlockState state : states){
            Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
 
                @Override
                public void run() {
                    state.update(true);
                }
            }, new Random().nextInt(10*20) + 20);
        }
    }
 
}