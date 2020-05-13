DROP TABLE IF EXISTS game_log;
DROP TABLE IF EXISTS at_bat;
DROP TABLE IF EXISTS half_inning;
DROP TABLE IF EXISTS hitter_record;
DROP TABLE IF EXISTS pitcher_record;
DROP TABLE IF EXISTS team_game;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS team;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    email VARCHAR(64) UNIQUE NOT NULL ,
    nickname VARCHAR(64) NOT NULL ,
    win INT NOT NULL ,
    lose INT NOT NULL ,
    draw INT NOT NULL
);

CREATE TABLE team (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(64) UNIQUE NOT NULL ,
    url VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255) NOT NULL,
    color VARCHAR(64) NOT NULL
);

CREATE TABLE player (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(64) NOT NULL ,
    is_pitcher BOOLEAN NOT NULL ,
    average FLOAT NOT NULL ,
    batting_order INT NOT NULL ,
    team INT REFERENCES team(id)
);

CREATE TABLE game (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    home_batting_order INT DEFAULT 1 NOT NULL ,
    away_batting_order INT DEFAULT 1 NOT NULL ,
    is_over BOOLEAN DEFAULT FALSE
);

CREATE TABLE team_game (
    team INT REFERENCES team(id),
    game INT REFERENCES game(id),
    user INT REFERENCES user(id),
    is_home BOOLEAN NOT NULL ,
    PRIMARY KEY (team, game)
);

CREATE TABLE pitcher_record (
    pitch INT DEFAULT 0 NOT NULL ,
    team_game_team INT REFERENCES team_game(team),
    team_game_game INT REFERENCES team_game(game),
    player INT REFERENCES player(id),
    PRIMARY KEY (team_game_team, team_game_game, player)
);

CREATE TABLE hitter_record (
    at_bat INT DEFAULT 0 NOT NULL ,
    hit INT DEFAULT 0 NOT NULL ,
    outs INT DEFAULT 0 NOT NULL ,
    team_game_team INT REFERENCES team_game(team),
    team_game_game INT REFERENCES team_game(game),
    player INT REFERENCES player(id),
    PRIMARY KEY (team_game_team, team_game_game, player)
);

CREATE TABLE half_inning (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    score INT DEFAULT 0 NOT NULL ,
    outs INT DEFAULT 0 NOT NULL ,
    strikes INT DEFAULT 0 NOT NULL ,
    balls INT DEFAULT 0 NOT NULL ,
    is_top BOOLEAN NOT NULL ,
    inning INT NOT NULL ,
    first_base BOOLEAN DEFAULT false NOT NULL ,
    second_base BOOLEAN DEFAULT false NOT NULL ,
    third_base BOOLEAN DEFAULT false NOT NULL ,
    home_base BOOLEAN DEFAULT false NOT NULL ,
    game INT REFERENCES game(id)
);

CREATE TABLE game_log (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    result ENUM('STRIKE', 'HIT', 'BALL', 'OUT') NOT NULL ,
    create_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    pitcher INT REFERENCES player(id) ,
    half_inning INT REFERENCES half_inning(id) ,
    hitter INT REFERENCES player(id)
);
