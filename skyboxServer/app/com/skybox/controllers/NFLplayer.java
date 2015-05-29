package com.skybox.controllers;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.skybox.dbmanager.NFLplayerstatsDBManager;
import com.skybox.model.NFLplayerstats;

/**
 * 
 * @author jialunliu
 *
 * This code is for handling Http request to NFLplayerStat table
 */
public class NFLplayer extends Controller {

	/**
	 * @return Json NFLplayerstats list, 200(success)
	 * 
	 * Call the getList() in the NFLplayerstatsDBManager and retrieve List 
	 * of NFLplayerstats from the Database
	 */
	public static Result getList() {
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		List<NFLplayerstats> playerList = new ArrayList<NFLplayerstats>();
		Gson gson = new Gson(); 
		
		npdbm.connect();
		playerList = npdbm.getList();
		npdbm.closeConnection();
		
		return ok(gson.toJson(playerList));
	}

	/**
	 * @return Json NFLplayerstats Object, 200(success)/404(Not Found)
	 * 
	 * Call the getItem() in the NFLplayerstatsDBManager and retrieve the 
	 * desired NFLplayerstats entry from the Database
	 */
	public static Result getItem(int id) {
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		NFLplayerstats np = null;
		Gson gson = new Gson(); 
		
		npdbm.connect();
		np = npdbm.getItem(id);
		npdbm.closeConnection();
		
		if (np == null) {
			return notFound(Json.toJson("Oops, no matched player"));
		}
		return ok(gson.toJson(np));
	}
	
	/**
	 * @return url of the inserted NFL player stats, 
	 * 		   201(Created)/400(Bad Request)
	 * 
	 * Inserting new NFL player stats into the Database and return the url of
	 * the inserted NFL player stats if succeeds
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result createItem() {
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		int rank = 0;
		JsonNode json = request().body().asJson();
				
		NFLplayerstats np = parseNFLplayer(0, json);
		
		npdbm.connect();
		rank = npdbm.createItem(np);
		npdbm.closeConnection();
		
		if (rank != 0) {
			return created(Json.toJson("http://localhost:9000/nflstats/" + rank));
		}
		// Not sure about the status code here, should be revised later.
		return badRequest(Json.toJson("NFL player stats insertion failed"));	
	}
	
	/**
	 * @param id
	 * @return 204(No Content)/404(Not Found)/400(Bad Request)
	 * 
	 * Modifying an existing NFL player stat in the Database
	 * User shouldn't be allowed to change the rank(id) at all.
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateItem(int id) {
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
		}
		// Not sure about the status code here, should be revised later.
		return badRequest(Json.toJson("NFL player stat update failed"));
	}
	
	/**
	 * @param id
	 * @param json
	 * @return NFLplayerstats Object
	 * 
	 * Parse the received json object from the PUT request into the
	 * NFLplayerstats object
	 */
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
	
	/**
	 * @param id
	 * @return 204(No Content)/404(Not Found)/400(Bad Request)
	 * 
	 * Deleting NFL player stats from the Database
	 */
	public static Result deleteItem(int id) {
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
		}
		// Not sure about the status code here, should be revised later.
		return badRequest(Json.toJson("User deletion failed"));
	}
}