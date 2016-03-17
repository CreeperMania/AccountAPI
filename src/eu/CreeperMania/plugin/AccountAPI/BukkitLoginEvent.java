package eu.CreeperMania.plugin.AccountAPI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BukkitLoginEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		if(Config.getConfig().getBoolean("auto-account.enable"))
		{
			if(!Config.getConfig().getBoolean("auto-account.permission") || e.getPlayer().hasPermission("account.autogenerate")){
				if(!AccountAPI.checkUser(e.getPlayer()))
				{
					AccountAPI.addUser(e.getPlayer(), Config.getConfig().getString("auto-account.password-hash"), true);
				}
			}
		}
	}
}
