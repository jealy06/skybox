package com.skybox.dbmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.skybox.model.User;

/*
 * This code is to make database queries according to
 * the requests received by Userinfo.java file
 * 
 */
public class UserDBManager extends DBManager<User> {

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
			System.out.println("Unable to retrieve the list of users...");
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public User getItem(int id) {
		// Retrieve single user from the Users table
		PreparedStatement ps = null;
		ResultSet rs = null;
		User usr = null;
		
		String sql = "select * from Users where userId = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			// There is always a single entry
			if (rs.next()) {
				int userId = rs.getInt("userId");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				usr = new User(userId, userName, password);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the target user...");
			e.printStackTrace();
		}
		
		return usr;
	}

	@Override
	public int createItem(User newUser) {
		// Insert a new user into the Users table
		int userId = 0;
		
		String sql = "INSERT INTO Users (userId, userName, password)" 
				+ "VALUES (0, '" + newUser.getUserName() + "', '"
				+ newUser.getPassword() + "');";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			// Retrieve the auto incremented key from the database
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
	            userId=rs.getInt(1);
	        }
			
		} catch (SQLException e) {
			System.out.println("Unable create new User");
			e.printStackTrace();
		}

		return userId;
	}

	@Override
	public boolean updateItem(User entry) {
		// Modify an existing user in the Users table
		PreparedStatement ps = null;
		boolean success = false;
		
		String sql = "update Users set userName = ?, password = ? where userId = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, entry.getUserName());
			ps.setString(2, entry.getPassword());
			ps.setInt(3, entry.getUserId());
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.out.println("Unable to modify user entry...");
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean deleteItem(int id) {
		// Delete a user from the Users table
		PreparedStatement ps = null;
		boolean success = false;
		
		String sql = "delete from Users where userId = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.out.println("Unable to delete entry from User table");
			e.printStackTrace();
		}
		return success;
	}
}
