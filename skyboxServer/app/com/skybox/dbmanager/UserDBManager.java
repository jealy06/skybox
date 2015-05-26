package com.skybox.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.skybox.model.User;

public class UserDBManager implements DBManager {
	
	private Connection conn = null;
	
	@Override
	public void connect() {
		try {
	    	try {
				Class.forName(JDBC_DRIVER).newInstance();
			} catch (InstantiationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			conn=DriverManager.getConnection(DB_URL);
			System.out.println("Connect!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<User> getList() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getItem(int id) {
		// TODO Auto-generated method stub
		return null;
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
