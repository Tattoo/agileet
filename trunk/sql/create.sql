   CREATE DATABASE pokkare CHARACTER SET utf8;


DROP TABLE games;
DROP TABLE score;
DROP TABLE player;
DROP TABLE points;

CREATE TABLE player (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY contestant_key (id)
) ENGINE=InnoDB;

CREATE TABLE score (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	game_score_id INT UNSIGNED NOT NULL,
	player_id INT UNSIGNED NOT NULL,
	rank INT UNSIGNED NOT NULL,
	PRIMARY KEY score_key (id),
	FOREIGN KEY (player_id) REFERENCES player (id),
	UNIQUE (game_score_id, player_id)

) ENGINE=InnoDB;

CREATE TABLE games (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	description	VARCHAR(100),
	host INT UNSIGNED,
	game_date DATE,
	game_number INT UNSIGNED NOT NULL,
	game_score_id INT UNSIGNED,
  PRIMARY KEY game_key (id),
  /*FOREIGN KEY (game_score_id) REFERENCES score (game_score_id),*/
  FOREIGN KEY (host) REFERENCES player (id)
) ENGINE=InnoDB;

CREATE TABLE points (
	rank INT UNSIGNED NOT NULL,
	points INT UNSIGNED NOT NULL,
	PRIMARY KEY score_key (rank)
) ENGINE=InnoDB;



insert into player (name) values ('jouns');
insert into player (name) values ('tattoo');
insert into points values(0, 0);
insert into points values(1, 10);
insert into points values(2, 6);
insert into points values(3, 4);
insert into points values(4, 3);
insert into points values(5, 2);
insert into points values(6, 1);
insert into points values(7, 0);
insert into points values(8, 0);