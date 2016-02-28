package eu.CreeperMania.plugin.AccountAPI;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

class Config {
	
	private static FileConfiguration config = null;
	private static File configFile = null;
	
	static void reloadConfig(){
		if(configFile == null){
			configFile = new File(BukkitMain.getPlugin().getDataFolder(), "config.yml");
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		
		Reader defConfigStream =  new InputStreamReader(BukkitMain.getPlugin().getResource("config.yml"));
		if(defConfigStream != null){
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			config.setDefaults(defConfig);
		}
	}
	
	static FileConfiguration getConfig(){
		if(config == null){
			reloadConfig();
		}
		return config;
	}
	
	static void saveConfig(){
		if(config == null || configFile == null){
			return;
		}
		try{
			getConfig().save(configFile);
		}
		catch(IOException e){
			BukkitMain.getPlugin().getLogger().log(Level.SEVERE, "Unable to save config to " + configFile.getPath());
		}
	}
	
	static void saveDefaultConfig(){
		if(configFile == null){
			configFile = new File(BukkitMain.getPlugin().getDataFolder(), "config.yml");
		}
		if(!configFile.exists()){
			BukkitMain.getPlugin().saveResource("config.yml", false);
		}
	}
}
