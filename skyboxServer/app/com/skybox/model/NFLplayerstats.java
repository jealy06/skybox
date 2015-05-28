package com.skybox.model;

public class NFLplayerstats {

	private int rank;
	private String playerName;
	private String team;
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
	
	public NFLplayerstats(int rank, String playerName, String team, int comp,
			int att, float pct, int yds, float yds_a, int longp, int td,
			int intt, int sack, float rate, int yds_g) {
		super();
		this.rank = rank;
		this.playerName = playerName;
		this.team = team;
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

	@Override
	public String toString() {
		// For debugging purpose
		return "NFLplayerstats [rank=" + rank + ", playerName=" + playerName
				+ ", team=" + team + ", comp=" + comp + ", att=" + att
				+ ", pct=" + pct + ", yds=" + yds + ", yds_a=" + yds_a
				+ ", longp=" + longp + ", td=" + td + ", intt=" + intt
				+ ", sack=" + sack + ", rate=" + rate + ", yds_g=" + yds_g
				+ "]";
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getComp() {
		return comp;
	}

	public void setComp(int comp) {
		this.comp = comp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public float getPct() {
		return pct;
	}

	public void setPct(float pct) {
		this.pct = pct;
	}

	public int getYds() {
		return yds;
	}

	public void setYds(int yds) {
		this.yds = yds;
	}

	public float getYds_a() {
		return yds_a;
	}

	public void setYds_a(float yds_a) {
		this.yds_a = yds_a;
	}

	public int getLongp() {
		return longp;
	}

	public void setLongp(int longp) {
		this.longp = longp;
	}

	public int getTd() {
		return td;
	}

	public void setTd(int td) {
		this.td = td;
	}

	public int getIntt() {
		return intt;
	}

	public void setIntt(int intt) {
		this.intt = intt;
	}

	public int getSack() {
		return sack;
	}

	public void setSack(int sack) {
		this.sack = sack;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getYds_g() {
		return yds_g;
	}

	public void setYds_g(int yds_g) {
		this.yds_g = yds_g;
	}	
}
