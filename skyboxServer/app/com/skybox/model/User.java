package com.skybox.model;

public class User {
	private int userId;
	private String userName;
	private String password;

	public User() {
		
	}
	
	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	
	
	@Override
	public String toString() {
		// For debugging purpose
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
