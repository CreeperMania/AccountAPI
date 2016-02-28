package eu.CreeperMania.plugin.AccountAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeMain extends Plugin {
	
	static Configuration config;
	
	@Override
	public void onEnable(){
		
		AccountAPI.bungeecord = true;
		try{
			config = config();
		} catch(IOException e){
			throw new RuntimeException("Unable to load or create config file", e);
		}
		
		MySQL.getData();
		if(config.getBoolean("check-connection")){
			try
			{
				MySQL.connect();
				Queries.createTable();
				getLogger().log(Level.INFO, "Successfully connected to MySQL database.");
				MySQL.disconnect();
			}
			catch(SQLException e)
			{
				getLogger().log(Level.SEVERE, "Unable to connect to database. Edit connection data in config.yml");
				getLogger().log(Level.SEVERE, "The plugin will not work without a working MySQL connection.");
				e.printStackTrace();
			}
		}
	}
	
	
	
	private Configuration config() throws IOException{
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}
		File configFile = new File(getDataFolder(), "config.yml");
		if(!configFile.exists()){
			configFile.createNewFile();
			ByteStreams.copy(getResourceAsStream("config.yml"), new FileOutputStream(configFile));
		}
		return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
	}
	
}
