package com.skybox.dbmanager;

import java.util.ArrayList;

/*
 * This is the interface of all the database manager, all the DBManager in this package is used
 * to make sql queries to the database and send back the results if available
 */
public abstract class DBManager<O> {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://myskyboxdb.cwu2xrntuwr7.us-west-2.rds.amazonaws.com"
			+ ":3306/NFLDB?user=skyboxplay2015&password=Fantasytakeover68";
	
	public void connect() {
	}
	
	public ArrayList<? extends Object> getList() {
		return null;
	}
	
	public Object getItem(int id) {
		return null;
	}
	
	public boolean createItem(O entry) {
		return false;
	}
	
	public boolean updateItem(int id) {
		return false;
	}
	
	public boolean deleteItem(int id) {
		return false;
	}
	
	public void closeConnection() {
	}
}
