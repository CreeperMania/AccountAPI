package eu.CreeperMania.plugin.AccountAPI;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AccountAPI {
	
	static boolean bungeecord;
	
	static void log(Level logLevel, String message){
		if(bungeecord){
			ProxyServer.getInstance().getLogger().log(logLevel, message);
		}else{
			Bukkit.getLogger().log(logLevel, message);
		}
	}
	
	private static String uuid(Player player)
	{
		return player.getUniqueId().toString().replace("-", "");
	}
	private static String uuid(ProxiedPlayer player)
	{
		return player.getUniqueId().toString().replace("-", "");
	}
	
	public static boolean addUser(Player player)
	{
		return Queries.addUser(uuid(player), player.getName(), "");
	}
	public static boolean addUser(ProxiedPlayer player)
	{
		return Queries.addUser(uuid(player), player.getName(), "");
	}

	public static boolean addUser(String uuid)
	{
		return Queries.addUser(uuid.replace("-", ""), "", "");
	}
	
	public static boolean addUser(String uuid, String name)
	{
		return Queries.addUser(uuid.replace("-", ""), name, "");
	}
	
	public static boolean addUser(Player player, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.addUser(uuid(player), player.getName(), password);
	}
	public static boolean addUser(ProxiedPlayer player, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.addUser(uuid(player), player.getName(), password);
	}
	
	public static boolean addUser(String uuid, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.addUser(uuid.replace("-", ""), "", password);
	}
	
	public static boolean addUser(String uuid, String name, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.addUser(uuid.replace("-", ""), name, password);
	}
	
	
	
	public static boolean checkUser(Player player)
	{
		return Queries.checkUser(uuid(player));
	}
	public static boolean checkUser(ProxiedPlayer player)
	{
		return Queries.checkUser(uuid(player));
	}
	
	public static boolean checkUser(String uuid)
	{
		return Queries.checkUser(uuid.replace("-", ""));
	}
	
	@Deprecated
	public static boolean checkUserByName(String name)
	{
		return Queries.checkUserByName(name);
	}
	
	
	
	public static String getName(Player player)
	{
		return Queries.getName(uuid(player));
	}
	public static String getName(ProxiedPlayer player)
	{
		return Queries.getName(uuid(player));
	}
	
	public static String getName(String uuid)
	{
		return Queries.getName(uuid.replace("-", ""));
	}
	
	
	
	public static boolean updateName(Player player)
	{
		return Queries.setName(uuid(player), player.getName());
	}
	public static boolean updateName(ProxiedPlayer player)
	{
		return Queries.setName(uuid(player), player.getName());
	}
	
	
	
	public static boolean setName(Player player, String newName)
	{
		return Queries.setName(uuid(player), newName);
	}
	public static boolean setName(ProxiedPlayer player, String newName)
	{
		return Queries.setName(uuid(player), newName);
	}
	
	public static boolean setName(String uuid, String newName)
	{
		return Queries.setName(uuid.replace("-", ""), newName);
	}
	
	
	
	public static String getPasswordHash(Player player)
	{
		return Queries.getPasswordHash(uuid(player));
	}
	public static String getPasswordHash(ProxiedPlayer player)
	{
		return Queries.getPasswordHash(uuid(player));
	}
	
	public static String getPasswordHash(String uuid)
	{
		return Queries.getPasswordHash(uuid.replace("-", ""));
	}
	
	@Deprecated
	public static String getPasswordHashByName(String name)
	{
		return Queries.getPasswordHashByName(name);
	}
	
	public static boolean setPassword(Player player, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.setPassword(uuid(player), password);
	}
	public static boolean setPassword(ProxiedPlayer player, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.setPassword(uuid(player), password);
	}
	
	public static boolean setPassword(String uuid, String password, boolean hashed)
	{
		if(!hashed)
		{
			password = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
		}
		return Queries.setPassword(uuid.replace("-", ""), password);
	}
	
	
	
	public static boolean deleteUser(Player player)
	{
		return Queries.deleteUser(uuid(player));
	}
	public static boolean deleteUser(ProxiedPlayer player)
	{
		return Queries.deleteUser(uuid(player));
	}
	
	public static boolean deleteUser(String uuid)
	{
		return Queries.deleteUser(uuid.replace("-", ""));
	}
}
