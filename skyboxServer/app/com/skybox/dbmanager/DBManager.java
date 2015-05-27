package com.skybox.dbmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * This is the interface of all the database manager, all the DBManager in this package is used
 * to make sql queries to the database and send back the results if available
 */
public interface DBManager {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://myskyboxdb.cwu2xrntuwr7.us-west-2.rds.amazonaws.com"
			+ ":3306/NFLDB?user=skyboxplay2015&password=Fantasytakeover68";
	
	public void connect();
	public ArrayList<? extends Object> getList();
	public Object getItem(int id);
	public boolean createItem();
	public boolean updateItem(int id);
	public boolean deleteItem(int id);
	
	public void closeConnection();
}
