package eu.CreeperMania.plugin.AccountAPI;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeLoginEvent implements Listener {
	
	@EventHandler
	public void onLogin(PostLoginEvent e)
	{
		if(BungeeMain.config.getBoolean("auto-account.enable"))
		{
			if(!BungeeMain.config.getBoolean("auto-account.permission") || e.getPlayer().hasPermission("account.autogenerate")){
				if(!AccountAPI.checkUser(e.getPlayer()))
					AccountAPI.addUser(e.getPlayer(), BungeeMain.config.getString("auto-account.password-hash"), true);
			}
		}
	}

}
