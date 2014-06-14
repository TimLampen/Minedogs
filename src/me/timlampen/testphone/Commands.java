package me.timlampen.testphone;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use PogoTeams!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("minedogs")|| cmd.getName().equalsIgnoreCase("md")) {
			if (args.length == 0) {
				player.sendMessage("             -----=<MineDogs Help>=-----             ");
				player.sendMessage("/minedogs reload -- Reloads The Plugin");
				player.sendMessage("/minedogs kits -- Shows a List of Availiable Kits");
				player.sendMessage("/minedogs help -- Gives A Beginner Book");
			}
			if(args.length>=1){
				if(args[2].equalsIgnoreCase("kits")){
					
				}
			}
		}
		return true;
	}

}
