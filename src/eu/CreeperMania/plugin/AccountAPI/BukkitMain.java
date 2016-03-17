package eu.CreeperMania.plugin.AccountAPI;

import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {
	
	public static BukkitMain plugin;
	
	public static Plugin getPlugin()
	{
		return plugin;
	}
	
	public void onEnable()
	{
		plugin = this;
		AccountAPI.bungeecord = false;
		Config.saveDefaultConfig();
		
		MySQL.getData();
		if(Config.getConfig().getBoolean("check-connection")){
			try
			{
				MySQL.connect();
				Queries.createTable();
				getLogger().log(Level.INFO, "Successfully connected to MySQL database.");
				MySQL.disconnect();
			}
			catch(SQLException e)
			{
				getLogger().log(Level.WARNING, "Unable to connect to database. Edit connection data in config.yml");
				e.printStackTrace();
				getPlugin().getServer().getPluginManager().disablePlugin(this);
			}
		}
		
		getServer().getPluginManager().registerEvents(new BukkitLoginEvent(), this);
	}
}
