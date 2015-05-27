package com.skybox.controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
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
		
		if (usr == null) {
			return notFound(Json.toJson("Oops, no matched user"));
		} else {
			return ok(Json.toJson(usr));
		}
		
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result createItem() {
		// Inserting new user into the Database
		UserDBManager udbm = new UserDBManager();
		int userId;
		JsonNode json = request().body().asJson();
		
		String userName = json.findPath("userName").asText();
		String password = json.findPath("password").asText();
		User usr = new User(0, userName, password);
		
		udbm.connect();
		userId = udbm.createItem(usr);
		udbm.closeConnection();
		
		if (userId != 0) {
			return created(Json.toJson("http://localhost:9000/users/" + userId));
		} else {
			// Not sure about the status code here, should be revised later.
			return badRequest(Json.toJson("User creation failed"));
		}
		
	}

	public static Result updateItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Result deleteItem(int id) {
		// Deleting user from the Database
		UserDBManager udbm = new UserDBManager();
		User usr = null;
		boolean success = false;
		
		udbm.connect();
		usr = udbm.getItem(id);
		
		if (usr == null) {
			return notFound(Json.toJson("User not found"));
		} else {
			success = udbm.deleteItem(id);
		}
		udbm.closeConnection();
		
		if (success) {
			return noContent();
		} else {
			return badRequest(Json.toJson("User deletion failed"));
		}
		
	}
}
