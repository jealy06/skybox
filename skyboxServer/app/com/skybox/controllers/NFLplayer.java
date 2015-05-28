package com.skybox.controllers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.skybox.dbmanager.NFLplayerstatsDBManager;
import com.skybox.dbmanager.UserDBManager;
import com.skybox.model.NFLplayerstats;
import com.skybox.model.User;

import play.libs.Json;
import play.mvc.BodyParser;
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
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result createItem() {
		// Inserting new NFL player stat into the Database
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		int rank = 0;
		JsonNode json = request().body().asJson();
				
		NFLplayerstats np = parseNFLplayer(0, json);
		
		npdbm.connect();
		rank = npdbm.createItem(np);
		npdbm.closeConnection();
		
		if (rank != 0) {
			return created(Json.toJson("http://localhost:9000/nflstats/" + rank));
		} else {
			// Not sure about the status code here, should be revised later.
			return badRequest(Json.toJson("NFL player stats insertion failed"));
		}
		
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateItem(int id) {
		/**
		 * Modifying an existing NFL player stat in the Database
		 * User shouldn't be allowed to change the rank(id) at all.
		 */
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		boolean success = false;
		NFLplayerstats checkPlayer = null;
		NFLplayerstats np = null;
		JsonNode json = request().body().asJson();
		
		np = parseNFLplayer(id, json);
		
		npdbm.connect();
		// Check if the user exist first
		checkPlayer = npdbm.getItem(id);
		if (checkPlayer == null) {
			return notFound(Json.toJson("NFL player stat not found"));
		} else {
			// Update the existing user
			success = npdbm.updateItem(np);
		}
		npdbm.closeConnection();
		
		if (success) {
			return noContent();
		} else {
			// Not sure about the status code here, should be revised later.
			return badRequest(Json.toJson("NFL player stat update failed"));
		}
	}
	
	
	private static NFLplayerstats parseNFLplayer(int id, JsonNode json) {
		String playerName = json.findPath("playerName").asText();
		String team = json.findPath("team").asText();
		int comp = json.findPath("COMP").asInt();
		int att = json.findPath("ATT").asInt();
		float pct = json.findPath("PCT").floatValue();
		int yds = json.findPath("YDS").asInt();
		float yds_a = json.findPath("YDS_A").floatValue();
		int longp = json.findPath("LONGP").asInt();
		int td = json.findPath("TD").asInt();
		int intt = json.findPath("INTT").asInt();
		int sack = json.findPath("SACK").asInt();
		float rate = json.findPath("RATE").floatValue();
		int yds_g = json.findPath("YDS_G").asInt();
		
		/**
		 * rank value could be anything here
		 * Since rank field is set to auto_increment, it will not take any value
		 * from outside
		 * 
		 * rank only matters when user trying to update an existing NFL player stat
		 */
		NFLplayerstats np = new NFLplayerstats(id, playerName, team, comp, 
				att, pct, yds, yds_a, longp, td, intt, sack, rate, yds_g);
		System.out.println(np.toString());
		
		return np;
	}
	
	public static Result deleteItem(int id) {
		// Deleting NFL player stat from the Database
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		NFLplayerstats np = null;
		boolean success = false;
		
		npdbm.connect();
		np = npdbm.getItem(id);
		
		if (np == null) {
			return notFound(Json.toJson("NFL player not found"));
		} else {
			success = npdbm.deleteItem(id);
		}
		npdbm.closeConnection();
		
		if (success) {
			return noContent();
		} else {
			return badRequest(Json.toJson("User deletion failed"));
		}
		
	}
}