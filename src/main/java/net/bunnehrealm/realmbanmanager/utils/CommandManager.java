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

import net.bunnehrealm.realmbanmanager.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{
	MainClass MainClass;
	net.bunnehrealm.realmbanmanager.utils.BanManager BanManager = new net.bunnehrealm.realmbanmanager.utils.BanManager(MainClass);

	public CommandManager(MainClass MainClass) {
		this.MainClass = MainClass;
	}
	
	public CommandManager(net.bunnehrealm.realmbanmanager.utils.BanManager BanManager){
		this.BanManager = BanManager;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args){
		return false;
	}
	
}
