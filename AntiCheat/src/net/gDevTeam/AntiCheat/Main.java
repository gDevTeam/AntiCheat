package net.gDevTeam.AntiCheat;

import org.bukkit.plugin.java.JavaPlugin;

import net.gDevTeam.AntiCheat.AntiReach.Reach_A;

public class Main
extends JavaPlugin
{
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new Reach_A(this), this);
	}
}
