CREATE TABLE Users (
	user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, -- This could be a hashcode as well
	user_name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    DOB DATE NOT NULL,
    email VARCHAR(50) NOT NULL,
    college VARCHAR(100),
    -- picture store in DB or server file system?
    favorite_sports_to_play VARCHAR(255),
    favorite_sports_to_watch VARCHAR(255),
    favorite_professional_athlete VARCHAR(255),
    favorite_professional_team VARCHAR(255),
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- this field autoinitializes
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                                  ON UPDATE CURRENT_TIMESTAMP -- this field autoinitializes and                                                               -- autoupdates
	PRIMARY KEY (user_id)
);

/*
 * Stores the player who participate the particular league
 */
CREATE TABLE League (
    league_id VARCHAR(255) NOT NULL,
    league_name VARCHAR(50) NOT NULL,
    league_password VARCHAR(50) NOT NULL,
    league_motto VARCHAR(255)
    PRIMARY KEY (league_id)
);

/* 
 * NFLplayerStat table is not finalized yet.
 */
CREATE TABLE NFLplayerStat (
    rank INT NOT NULL AUTO_INCREMENT,
    player_name VARCHAR(50) NOT NULL,
    team VARCHAR(50) NOT NULL,
    COMP INT NOT NULL,
    ATT INT NOT NULL,
    PCT FLOAT NOT NULL,
    YDS INT NOT NULL,
    YDS_A FLOAT NOT NULL,
    LONGP INT NOT NULL,
    TD INT NOT NULL,
    INTT INT NOT NULL,
    SACK INT NOT NULL,
    RATE FLOAT NOT NULL,
    YDS_G INT NOT NULL,
    PRIMARY KEY (rank)
);

/*
 * TODO:
 * 1. How to store pictures uploaded by users
 * 2. How to store the league each user is currently in and joined in the past.
 */
