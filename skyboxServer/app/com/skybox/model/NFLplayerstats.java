package com.skybox.model;

public class NFLplayerstats {
	private int rank;
	private String playerName;
	private String team;
	private int COMP;
	private int ATT;
	private float PCT;
	private int YDS;
	private float YDS_A;
	private int LONGP;
	private int TD;
	private int INTT;
	private int SACK;
	private float RATE;
	private int YDS_G;
    
	public NFLplayerstats(String playerName, String team, int COMP, int ATT,
			float PCT, int YDS, float YDS_A, int lONGP, int TD, int INTT,
			int SACK, float RATE, int YDS_G) {
		this.playerName = playerName;
		this.team = team;
		this.COMP = COMP;
		this.ATT = ATT;
		this.PCT = PCT;
		this.YDS = YDS;
		this.YDS_A = YDS_A;
		this.LONGP = lONGP;
		this.TD = TD;
		this.INTT = INTT;
		this.SACK = SACK;
		this.RATE = RATE;
		this.YDS_G = YDS_G;
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

	public int getCOMP() {
		return COMP;
	}

	public void setCOMP(int cOMP) {
		COMP = cOMP;
	}

	public int getATT() {
		return ATT;
	}

	public void setATT(int aTT) {
		ATT = aTT;
	}

	public float getPCT() {
		return PCT;
	}

	public void setPCT(float pCT) {
		PCT = pCT;
	}

	public int getYDS() {
		return YDS;
	}

	public void setYDS(int yDS) {
		YDS = yDS;
	}

	public float getYDS_A() {
		return YDS_A;
	}

	public void setYDS_A(float yDS_A) {
		YDS_A = yDS_A;
	}

	public int getLONGP() {
		return LONGP;
	}

	public void setLONGP(int lONGP) {
		LONGP = lONGP;
	}

	public int getTD() {
		return TD;
	}

	public void setTD(int tD) {
		TD = tD;
	}

	public int getINTT() {
		return INTT;
	}

	public void setINTT(int iNTT) {
		INTT = iNTT;
	}

	public int getSACK() {
		return SACK;
	}

	public void setSACK(int sACK) {
		SACK = sACK;
	}

	public float getRATE() {
		return RATE;
	}

	public void setRATE(float rATE) {
		RATE = rATE;
	}

	public int getYDS_G() {
		return YDS_G;
	}

	public void setYDS_G(int yDS_G) {
		YDS_G = yDS_G;
	}
}
