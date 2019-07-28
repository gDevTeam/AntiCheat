package net.gDevTeam.AntiCheat.AntiReach;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import net.gDevTeam.AntiCheat.Main;

public class Reach_A
implements Listener
{
	private Main m;
	
	public Reach_A(Main m) {
		this.m = m;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	void onHit(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player player = (Player)e.getDamager();
			Player target = (Player)e.getEntity();
			if(player.getGameMode() == GameMode.CREATIVE && !(player.hasPermission("AntiCheat.bypass.Reach_A"))) return;
			double distance = player.getLocation().distance(target.getLocation());
			int ping = ((CraftPlayer)player).getHandle().ping;
			if(ping > 100){
				distance += 0.25;
			}else if(ping > 250) {
				distance += 0.35;
			}else if(ping > 450) {
				distance += 0.45;
			}else if(ping > 600) {
				distance += 0.6;
			}
			if(!(e.getCause().equals(DamageCause.PROJECTILE))) {
				if(distance > 4.5) 
					e.setCancelled(true);{
					for(Player a : Bukkit.getOnlinePlayers()) {
						if(a.hasPermission("AntiCheat.notify")) a.sendMessage(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Notify.REACH_A").replace("{player}", player.getName()).replace("{ping}", String.valueOf(ping))));
					}
				}
			}
		}
	}
}
