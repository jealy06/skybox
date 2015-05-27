package com.skybox.controllers;

import java.util.ArrayList;

import com.skybox.dbmanager.UserDBManager;
import com.skybox.model.User;

import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

/*
 * This code is for handling Http request to Users table
 */

public class Userinfo extends Controller {

	// Retrieving List of Users from the Database
	public static Result getList() {
		UserDBManager udbm = new UserDBManager();
		ArrayList<User> userList = new ArrayList<>();
		
		udbm.connect();
		userList = udbm.getList();
		udbm.closeConnection();
		
		return ok(Json.toJson(userList));
	}

	// Retrieving single user from the Database
	public static Result getItem(int id) {
		UserDBManager udbm = new UserDBManager();
		User usr = null;
		
		udbm.connect();
		usr = udbm.getItem(id);
		udbm.closeConnection();
		
		if (usr.getUserName() == null) {
			return notFound(Json.toJson("Oops, no matched user"));
		} else {
			return ok(Json.toJson(usr));
		}
		
	}

	public static Result createItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Result updateItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Result deleteItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
