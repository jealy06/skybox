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
		// Inserting new user into the Database
		NFLplayerstatsDBManager npdbm = new NFLplayerstatsDBManager();
		int rank = 0;
		JsonNode json = request().body().asJson();
		
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
				
		NFLplayerstats np = new NFLplayerstats(rank, playerName, team, comp, 
				att, pct, yds, yds_a, longp, td, intt, sack, rate, yds_g);
		System.out.println(np.toString());
		
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
}