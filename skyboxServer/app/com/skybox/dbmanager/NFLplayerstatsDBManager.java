package com.skybox.dbmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skybox.model.NFLplayerstats;

/**
 * 
 * @author jialunliu
 *
 * This code is to make database queries according to the requests received by 
 * NFLplayer.java file
 */
public class NFLplayerstatsDBManager extends DBManager<NFLplayerstats> {

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#getList()
	 * 
	 * Retrieve list of NFLplayerstats from the NFLplayerStat table
	 */
	@Override
	public List<NFLplayerstats> getList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NFLplayerstats> playerList = new ArrayList<NFLplayerstats>();
		
		String sql = "select * from NFLplayerStat";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				NFLplayerstats np = parseObject(rs);
				playerList.add(np);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the list of NFL player stats...");
			e.printStackTrace();
		}
		
		return playerList;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#getItem(int)
	 * 
	 * Retrieve list of NFLplayerstats from the NFLplayerStat table
	 */
	@Override
	public NFLplayerstats getItem(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		NFLplayerstats np = null;
		
		String sql = "select * from NFLplayerStat where rank = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				np = parseObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve the list of NFL player stats...");
			e.printStackTrace();
		}
		
		return np;
	}
	
	/**
	 * @param rs
	 * @return NFLplayerstats Object
	 * @throws SQLException
	 * 
	 * Parse the ResultSet received from the database, and parse it to
	 * be a NFLplayerstats object.
	 */
	private NFLplayerstats parseObject(ResultSet rs) throws SQLException {
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
		
		return np;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#createItem(java.lang.Object)
	 * 
	 * Insert a new NFL player stat into the NFLplayerStat table and send 
	 * back the rank(id) of the new inserted row.
	 */
	@Override
	public int createItem(NFLplayerstats entry) {
		// 
		int rank = 0;
		
		String sql = "INSERT INTO NFLplayerStat (rank, playerName, team, COMP, ATT, PCT,"
				+ " YDS, YDS_A, LONGP, TD, INTT, SACK, RATE, YDS_G)" 
				+ "VALUES (0, '" + entry.getPlayerName() + "', '"
				+ entry.getTeam() + "', " + entry.getComp() + ", " + entry.getAtt() + ", "
				+ entry.getPct() + ", " + entry.getYds() + ", " + entry.getYds_a() + ", "
				+ entry.getLongp() + ", " + entry.getTd() + ", " + entry.getIntt() + ", "
				+ entry.getSack() + ", " + entry.getRate() + ", " + entry.getYds_g() + ");";
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			// Retrieve the auto incremented key from the database
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
	            rank=rs.getInt(1);
	        }
			
		} catch (SQLException e) {
			System.out.println("Unable insert new NFL player stats");
			e.printStackTrace();
		}

		return rank;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#updateItem(java.lang.Object)
	 * 
	 * Modify an existing NFL player stat in the NFLplayerStat table
	 */
	@Override
	public boolean updateItem(NFLplayerstats entry) {
		PreparedStatement ps = null;
		boolean success = false;
		
		String sql = "update NFLplayerStat set playerName = ?, team = ?, COMP = ?, ATT = ?, "
				+ "PCT = ?, YDS = ?, YDS_A = ?, LONGP = ?, TD = ?, INTT = ?, SACK = ?, "
				+ "RATE = ?, YDS_G = ? where rank = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, entry.getPlayerName());
			ps.setString(2, entry.getTeam());
			ps.setInt(3, entry.getComp());
			ps.setInt(4, entry.getAtt());
			ps.setFloat(5, entry.getPct());
			ps.setInt(6, entry.getYds());
			ps.setFloat(7, entry.getYds_a());
			ps.setInt(8, entry.getLongp());
			ps.setInt(9, entry.getTd());
			ps.setInt(10, entry.getIntt());
			ps.setInt(11, entry.getSack());
			ps.setFloat(12, entry.getRate());
			ps.setInt(13, entry.getYds_g());
			ps.setInt(14, entry.getRank());
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.out.println("Unable to modify NFL player stat entry...");
			e.printStackTrace();
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.skybox.dbmanager.DBManager#deleteItem(int)
	 * 
	 * Delete a NFL player stat from the NFLplayerStat table
	 */
	@Override
	public boolean deleteItem(int id) {
		PreparedStatement ps = null;
		boolean success = false;
		
		String sql = "delete from NFLplayerStat where rank = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.out.println("Unable to delete entry from NFLplayerStat table");
			e.printStackTrace();
		}
		return success;
	}
}
