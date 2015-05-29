package com.skybox.model;

public class NFLplayerstats {

	private int rank;
	private PlayerInfo playerInfo;
	private PlayerStats playerStats;

	private class PlayerInfo {
		private String playerName;
		private String team;
		
		public PlayerInfo(String playerName, String team) {
			this.playerName = playerName;
			this.team = team;
		}
	}
	
	private class PlayerStats {
		private int comp;
		private int att;
		private float pct;
		private int yds;
		private float yds_a;
		private int longp;
		private int td;
		private int intt;
		private int sack;
		private float rate;
		private int yds_g;
		
		public PlayerStats(int comp, int att, float pct, int yds, float yds_a,
				int longp, int td, int intt, int sack, float rate, int yds_g) {
			super();
			this.comp = comp;
			this.att = att;
			this.pct = pct;
			this.yds = yds;
			this.yds_a = yds_a;
			this.longp = longp;
			this.td = td;
			this.intt = intt;
			this.sack = sack;
			this.rate = rate;
			this.yds_g = yds_g;
		}	
	}
	
	public NFLplayerstats(int rank, String playerName, String team, int comp,
			int att, float pct, int yds, float yds_a, int longp, int td,
			int intt, int sack, float rate, int yds_g) {
		this.rank = rank;
		
		this.playerInfo = new PlayerInfo(playerName, team);
		this.playerStats = new PlayerStats(comp, att, pct, yds, yds_a, longp, td, 
				intt, sack, rate, yds_g);
	}

	@Override
	public String toString() {
		// For debugging purpose
		return "NFLplayerstats [rank=" + rank + ", playerName=" + getPlayerName() + ", team="
				+ getTeam() + ", comp=" + getComp() + ", " + "att=" + getAtt() + ", pct=" 
				+ getPct() + ", yds=" + getYds() + ", yds_a=" + getYds_a() + ", longp=" + getLongp()
				+ ", td=" + getTd() + ", intt=" + getIntt() + ", sack=" + getSack() + ", rate=" 
				+ getSack() + ", yds_g=" + getYds_g() + "]";
	}
	
	
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}

	public PlayerStats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}
	
	public String getPlayerName() {
		return playerInfo.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerInfo.playerName = playerName;
	}

	public String getTeam() {
		return playerInfo.team;
	}

	public void setTeam(String team) {
		this.playerInfo.team = team;
	}
	
	public int getComp() {
		return playerStats.comp;
	}

	public void setComp(int comp) {
		this.playerStats.comp = comp;
	}

	public int getAtt() {
		return playerStats.att;
	}

	public void setAtt(int att) {
		this.playerStats.att = att;
	}

	public float getPct() {
		return playerStats.pct;
	}

	public void setPct(float pct) {
		this.playerStats.pct = pct;
	}

	public int getYds() {
		return playerStats.yds;
	}

	public void setYds(int yds) {
		this.playerStats.yds = yds;
	}

	public float getYds_a() {
		return playerStats.yds_a;
	}

	public void setYds_a(float yds_a) {
		this.playerStats.yds_a = yds_a;
	}

	public int getLongp() {
		return playerStats.longp;
	}

	public void setLongp(int longp) {
		this.playerStats.longp = longp;
	}

	public int getTd() {
		return playerStats.td;
	}

	public void setTd(int td) {
		this.playerStats.td = td;
	}

	public int getIntt() {
		return playerStats.intt;
	}

	public void setIntt(int intt) {
		this.playerStats.intt = intt;
	}

	public int getSack() {
		return playerStats.sack;
	}

	public void setSack(int sack) {
		this.playerStats.sack = sack;
	}

	public float getRate() {
		return playerStats.rate;
	}

	public void setRate(float rate) {
		this.playerStats.rate = rate;
	}

	public int getYds_g() {
		return playerStats.yds_g;
	}

	public void setYds_g(int yds_g) {
		this.playerStats.yds_g = yds_g;
	}
}