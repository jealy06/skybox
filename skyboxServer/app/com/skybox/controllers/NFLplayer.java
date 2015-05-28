package com.skybox.controllers;

import java.util.ArrayList;
import java.util.List;

import com.skybox.dbmanager.NFLplayerstatsDBManager;
import com.skybox.dbmanager.UserDBManager;
import com.skybox.model.NFLplayerstats;
import com.skybox.model.User;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class NFLplayer extends Controller {
	
	// Retrieving List of NFLplayerstats from the Database
	public static Result getList() {
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		List<NFLplayerstats> playerList = new ArrayList<NFLplayerstats>();
		
		npdbm.connect();
		playerList = npdbm.getList();
		npdbm.closeConnection();
		
		return ok(Json.toJson(playerList));
	}

	// Retrieving single NFLplayerstats from the Database
	public static Result getItem(int id) {
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		NFLplayerstats np = null;
		
		npdbm.connect();
		np = npdbm.getItem(id);
		npdbm.closeConnection();
		
		if (np == null) {
			return notFound(Json.toJson("Oops, no matched player"));
		} else {
			return ok(Json.toJson(np));
		}
		
	}
}