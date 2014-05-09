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
package net.bunnehrealm.realmbanmanager;

import net.bunnehrealm.realmbanmanager.listeners.JoinListener;
import net.bunnehrealm.realmbanmanager.utils.BanManager;
import net.bunnehrealm.realmbanmanager.utils.CommandManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public class MainClass extends JavaPlugin {
	public int timer;
	public MainClass MainClass;
	public JoinListener jl = new JoinListener(this);
	public BanManager bm = new BanManager(this);
	public File bansFile;
	public FileConfiguration bans;

	@Override
	public void onDisable() {
		bm.saveTime(timer);
		getServer().getLogger().info("REALM Ban Manager has been disabled");
	}

	@Override
	public void onEnable(){
		getServer().getLogger().info("REALM Ban Manager has been enabled");
		bansFile = new File(getDataFolder(), "bans.yml");
		bans = new YamlConfiguration();

			try {
				firstBanRun();
			} catch (Exception e) {
				e.printStackTrace();
			}
			loadBans();

			if(!(bans.contains("Timer"))){
				bans.set("Timer", 0);
			}

			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(jl, this);

			this.getCommand("ban").setExecutor(new CommandManager(this));

			timer = bm.loadTime();
			BukkitScheduler bs = getServer().getScheduler();
			bs.scheduleSyncRepeatingTask(this,
					new Runnable(){

						@Override
						public void run() {
							timer++;

						}

			}, 0, 20);

	}

	private void firstBanRun() throws Exception {
		if (!bansFile.exists()) {
			getLogger().info("Creating a bans.yml file");
			bansFile.getParentFile().mkdirs();
			bansFile.createNewFile();

		}
	}

	public void loadBans() {
		try {
			bans.load(bansFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveBans() {
		try {
			bans.save(bansFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
