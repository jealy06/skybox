package com.skybox.dbmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skybox.model.User;

/**
 * 
 * @author jialunliu
 *
 * This code is to make database queries according to the requests received by 
 * Userinfo.java file
 */
public class UserDBManager extends DBManager<User> {

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#getList()
	 * 
	 * Retrieve list of users from the Users table
	 */
	@Override
	public List<User> getList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		
		String sql = "select * from Users";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				User newUser = parseUser(rs);
				userList.add(newUser);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the list of users...");
			e.printStackTrace();
		}
		
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#getItem(int)
	 * 
	 * Retrieve single user from the Users table
	 */
	@Override
	public User getItem(int id) {
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
				usr = parseUser(rs);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the target user...");
			e.printStackTrace();
		}
		
		return usr;
	}
	
	/**
	 * @param rs
	 * @return User Object
	 * @throws SQLException
	 * 
	 * Parse the ResultSet received from the database, and parse it to
	 * be a User object.
	 */
	private User parseUser(ResultSet rs) throws SQLException {
		int userId = rs.getInt("userId");
		String userName = rs.getString("userName");
		String password = rs.getString("password");
		User usr = new User(userId, userName, password);
		
		return usr;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#createItem(java.lang.Object)
	 * 
	 * Insert a new user into the Users table and send back the userId of 
	 * the new inserted row.
	 */
	@Override
	public int createItem(User newUser) {
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

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#updateItem(java.lang.Object)
	 * 
	 * Modify an existing user in the Users table
	 */
	@Override
	public boolean updateItem(User entry) {
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

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#deleteItem(int)
	 * 
	 * Delete a user from the Users table
	 */
	@Override
	public boolean deleteItem(int id) {
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