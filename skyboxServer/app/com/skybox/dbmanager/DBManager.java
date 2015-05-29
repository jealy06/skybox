package com.skybox.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/*
 * This is the interface of all the database manager, all the DBManager in this package is used
 * to make sql queries to the database and send back the results if available
 */
public abstract class DBManager<O> {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://myskyboxdb.cwu2xrntuwr7.us-west-2.rds.amazonaws.com"
			+ ":3306/NFLDB?user=skyboxplay2015&password=Fantasytakeover68";
	
	public Connection conn = null;
	
	public void connect() {
		// Create an instance of connection to MySQL database
    	try {
			Class.forName(JDBC_DRIVER).newInstance();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

    	try {
			conn=DriverManager.getConnection(DB_URL);
			System.out.println("Connect!");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<? extends Object> getList() {
		return null;
	}
	
	public Object getItem(int id) {
		return null;
	}
	
	public int createItem(O entry) {
		return 0;
	}
	
	public boolean updateItem(O entry) {
		return false;
	}
	
	public boolean deleteItem(int id) {
		return false;
	}
	
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
