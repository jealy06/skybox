package com.skybox.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.skybox.model.User;

/*
 * This code is to make database queries according to
 * the requests received by Userinfo.java file
 * 
 */
public class UserDBManager implements DBManager {
	
	private Connection conn = null;
	
	@Override
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

	@Override
	public ArrayList<User> getList() {
		// Retrieve list of users from the Users table
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<User> userList = new ArrayList<>();
		
		String sql = "select * from Users";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int userId = rs.getInt("userId");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				
				User newUser = new User(userId, userName, password);
				userList.add(newUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public User getItem(int id) {
		// Retrieve single user from the Users table
		PreparedStatement ps = null;
		ResultSet rs = null;
		User usr = new User();
		
		String sql = "select * from Users where userId = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			// There is always a single entry
			if (rs.next()) {
				usr.setUserId(rs.getInt("userId"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usr;
	}

	@Override
	public boolean createItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItem(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
