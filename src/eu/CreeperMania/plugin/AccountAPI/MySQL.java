package eu.CreeperMania.plugin.AccountAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySQL {
	
	private static String host;
	private static int port;
	private static String user;
	private static String password;
	private static String database;
	private static Connection con;
	
	static void getData(){
		if(!AccountAPI.bungeecord){
			host = Config.getConfig().getString("mysql.host");
			port = Config.getConfig().getInt("mysql.port");
			user = Config.getConfig().getString("mysql.user");
			password = Config.getConfig().getString("mysql.pw");
			database = Config.getConfig().getString("mysql.db");
		}
		else{
			host = BungeeMain.config.getString("mysql.host");
			port = BungeeMain.config.getInt("mysql.port");
			user = BungeeMain.config.getString("mysql.user");
			password = BungeeMain.config.getString("mysql.pw");
			database = BungeeMain.config.getString("mysql.db");
		}
	}
	
	
	static void connect() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
	}
	
	static void disconnect(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static boolean isConnected(){
		return (con == null ? false : true);
	}
	
	static Connection getConnection(){
		return con;
	}

}