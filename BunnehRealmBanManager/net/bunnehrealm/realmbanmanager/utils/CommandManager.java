/*
 *  REALMBanManager: Used for issuing and maintaing bans on bukkit server
    Copyright (C) 2014  Rory Finnegan 

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
	
	Contact me at bunnehrealm@gmail.com
 */
package net.bunnehrealm.realmbanmanager.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.bunnehrealm.realmbanmanager.MainClass;

public class CommandManager implements CommandExecutor{
	MainClass MainClass;
	BanManager BanManager = new BanManager(MainClass);

	public CommandManager(MainClass MainClass) {
		this.MainClass = MainClass;
	}
	
	public CommandManager(BanManager BanManager){
		this.BanManager = BanManager;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
			String string = cmd.getName();
			if(string.equalsIgnoreCase("ban")){
				if(!(args.length != 1) && !(args.length != 2)){
					cs.sendMessage(ChatColor.RED + "Correct Usage: " + ChatColor.AQUA + "/ban <player> [reason]");
					return false;
				}
				else{
				if(cs.hasPermission("BanManager.ban") || cs.isOp() || !(cs instanceof Player)){
					StringBuilder sb = new StringBuilder();
					for(int x = 1; x < args.length; x++){
						sb.append(" ").append(args[x]);
					}
					String ban_msg = sb.toString();
					BanManager.Ban(MainClass.getServer().getPlayer(args[0]).getUniqueId().toString(), ban_msg);
				}
				else{
					cs.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}
			}
			}
		return false;
	}
	
}
