package com.skybox.dbmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skybox.model.NFLplayerstats;

public class NFLplayerstatsDBManager extends DBManager<NFLplayerstats> {

	@Override
	public List<NFLplayerstats> getList() {
		// Retrieve list of NFLplayerstats from the NFLplayerStat table
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NFLplayerstats> playerList = new ArrayList<NFLplayerstats>();
		
		String sql = "select * from NFLplayerStat";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int rank = rs.getInt("rank");
				String playerName = rs.getString("playerName");
				String team = rs.getString("team");
				int comp = rs.getInt("COMP");
				int att  = rs.getInt("ATT");
				float pct = rs.getFloat("PCT");
				int yds = rs.getInt("YDS");
				float yds_a = rs.getFloat("YDS_A");
				int longp = rs.getInt("LONGP");
				int td = rs.getInt("TD");
				int intt = rs.getInt("INTT");
				int sack = rs.getInt("SACK");
				float rate = rs.getFloat("RATE");
				int yds_g = rs.getInt("YDS_G");
				
				NFLplayerstats np = new NFLplayerstats(rank, playerName, team, comp, 
						att, pct, yds, yds_a, longp, td, intt, sack, rate, yds_g);
				playerList.add(np);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the list of NFL player stats...");
			e.printStackTrace();
		}
		
		return playerList;
	}

	@Override
	public NFLplayerstats getItem(int id) {
		// Retrieve list of NFLplayerstats from the NFLplayerStat table
		PreparedStatement ps = null;
		ResultSet rs = null;
		NFLplayerstats np = null;
		
		String sql = "select * from NFLplayerStat where rank = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int rank = rs.getInt("rank");
				String playerName = rs.getString("playerName");
				String team = rs.getString("team");
				int comp = rs.getInt("COMP");
				int att  = rs.getInt("ATT");
				float pct = rs.getFloat("PCT");
				int yds = rs.getInt("YDS");
				float yds_a = rs.getFloat("YDS_A");
				int longp = rs.getInt("LONGP");
				int td = rs.getInt("TD");
				int intt = rs.getInt("INTT");
				int sack = rs.getInt("SACK");
				float rate = rs.getFloat("RATE");
				int yds_g = rs.getInt("YDS_G");
				
				np = new NFLplayerstats(rank, playerName, team, comp, 
						att, pct, yds, yds_a, longp, td, intt, sack, rate, yds_g);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the list of NFL player stats...");
			e.printStackTrace();
		}
		
		return np;
	}

	@Override
	public int createItem(NFLplayerstats entry) {
		// TODO Auto-generated method stub
		return super.createItem(entry);
	}

	@Override
	public boolean updateItem(NFLplayerstats entry) {
		// TODO Auto-generated method stub
		return super.updateItem(entry);
	}

	@Override
	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		return super.deleteItem(id);
	}
}
