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
package net.bunnehrealm.realmbanmanager.listeners;

import net.bunnehrealm.realmbanmanager.MainClass;
import net.bunnehrealm.realmbanmanager.utils.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	MainClass MainClass;
	BanManager bm = new BanManager(MainClass);

	public JoinListener(MainClass MainClass) {
		this.MainClass = MainClass;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		if (MainClass.bans.getBoolean(p.getUniqueId().toString() + ".banned")
				|| MainClass.bans.getBoolean(p.getUniqueId() + ".permabanned")) {

			p.kickPlayer(bm.getReason(p.getUniqueId().toString()));

		}

		if (!(MainClass.bans.contains(p.getUniqueId().toString()))) {

			MainClass.bans.set(e.getPlayer().getUniqueId().toString(), " ");

		}
	}

}
