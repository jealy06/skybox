package com.skybox.model;

public class NFLplayerstats {
	public int rank;
    public String playerName;
    public String team;
    public int COMP;
    public int ATT;
    public float PCT;
    public int YDS;
    public float YDS_A;
    public int LONGP;
    public int TD;
    public int INTT;
    public int SACK;
    public float RATE;
    public int YDS_G;
    
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
}
