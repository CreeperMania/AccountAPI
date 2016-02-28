package eu.CreeperMania.plugin.AccountAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

class Queries {
	
	static void createTable() throws SQLException
	{
		PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `user` (`uuid` varchar(32) NOT NULL, `name` varchar(16) NOT NULL, `pw` varchar(64) NOT NULL, PRIMARY KEY (`uuid`))");
		ps.executeUpdate();
	}
	
	static boolean addUser(String uuid, String name, String hash)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO `user` (`uuid`, `name`, `pw`) VALUES (?, ?, ?)");
			ps.setString(1, uuid);
			ps.setString(2, name);
			ps.setString(3, hash);
			ps.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to add user to database.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
		
	}
	
	static boolean checkUser(String uuid)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM `user` WHERE `uuid` = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			return false;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to check user.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
		
	}
	
	static boolean checkUserByName(String name)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM `user` WHERE `name` = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			return false;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to check user.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
		
	}
	
	static String getName(String uuid)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT `name` FROM `user` WHERE `uuid` = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getString("name");
			}
			return null;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to get user name.");
			e.printStackTrace();
			return null;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
	static boolean setName(String uuid, String newName)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `user` SET `name` = ? WHERE `uuid` = ?");
			ps.setString(1, newName);
			ps.setString(2, uuid);
			ps.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to set user name.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
	static String getPasswordHash(String uuid)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT `pw` FROM `user` WHERE `uuid` = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getString("pw");
			}
			return null;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to get password hash.");
			e.printStackTrace();
			return null;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
	static String getPasswordHashByName(String name)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT `pw` FROM `user` WHERE `name` = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getString("pw");
			}
			return null;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to get password hash.");
			e.printStackTrace();
			return null;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
	static boolean setPassword(String uuid, String newHash)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE `user` SET  `pw` = ? WHERE `uuid` = ?");
			ps.setString(1, newHash);
			ps.setString(2, uuid);
			ps.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to set password hash.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
	static boolean deleteUser(String uuid)
	{
		try
		{
			MySQL.connect();
			PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM `user` WHERE `uuid` = ?");
			ps.setString(1, uuid);
			ps.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			AccountAPI.log(Level.WARNING, "Unable to delete user.");
			e.printStackTrace();
			return false;
		}
		finally
		{
			MySQL.disconnect();
		}
	}
	
}
