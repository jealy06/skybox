CREATE TABLE Users (
	user_id     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, -- This could be a hashcode as well
	user_name   VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(50) NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    DOB         DATE NOT NULL,
    email       VARCHAR(50) NOT NULL,
    college     VARCHAR(100),
    -- picture server file system would be a better choice
    favorite_sports_to_play         VARCHAR(255),
    favorite_sports_to_watch        VARCHAR(255),
    favorite_professional_athlete   VARCHAR(255),
    favorite_professional_team      VARCHAR(255),
    -- this field autoinitializes to current time
    create_date                     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
     -- this field autoinitializes to current time and auto update the time stamp
    last_update                     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                                    ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);

/*
 * This table stores all the exact path in the server's file system of all pictures
 * uploaded by each user.
 */
CREATE TABLE Photos (
    user_id         BIGINT UNSIGNED NOT NULL,
    image_id        tinyint(3)  NOT NULL UNIQUE,
    image_type      VARCHAR(25) NOT NULL default '',
    image_location  VARCHAR(25) NOT NULL,
    image_size      VARCHAR(25) NOT NULL DEFAULT '',
    image_category  VARCHAR(25) NOT NULL DEFAULT '',
    image_name      VARCHAR(25) NOT NULL DEFAULT '',
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    PRIMARY KEY (user_id, image_id)
);

/*
 * Stores the player who participate the particular league that is currently running
 */
CREATE TABLE LeagueCurr (
    user_id         BIGINT UNSIGNED NOT NULL,
    league_id       VARCHAR(255) NOT NULL,
    league_name     VARCHAR(50) NOT NULL,
    league_password VARCHAR(50) NOT NULL,
    league_motto    VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    PRIMARY KEY (user_id, league_id)
);

/*
 * Stores the player who participate the particular league that has already finished
 */
CREATE TABLE LeaguePast (
    user_id         BIGINT UNSIGNED NOT NULL,
    league_id       VARCHAR(255) NOT NULL,
    league_name     VARCHAR(50) NOT NULL,
    league_password VARCHAR(50) NOT NULL,
    league_motto    VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    PRIMARY KEY (user_id, league_id)
);

/* 
 * NFLplayerStat table is not finalized yet.
 */
CREATE TABLE NFLplayerStat (
    rank            INT NOT NULL AUTO_INCREMENT,
    player_name     VARCHAR(50) NOT NULL,
    team            VARCHAR(50) NOT NULL,
    COMP            INT NOT NULL,
    ATT             INT NOT NULL,
    PCT             FLOAT NOT NULL,
    YDS             INT NOT NULL,
    YDS_A           FLOAT NOT NULL,
    LONGP           INT NOT NULL,
    TD              INT NOT NULL,
    INTT            INT NOT NULL,
    SACK            INT NOT NULL,
    RATE            FLOAT NOT NULL,
    YDS_G           INT NOT NULL,
    PRIMARY KEY (rank)
);
