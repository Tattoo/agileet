ALTER TABLE player 
ADD COLUMN state CHAR(1) NOT NULL DEFAULT 'N'
;

CREATE TABLE state (
	state CHAR(1) NOT NULL,
	explanation VARCHAR(100) NOT NULL,
	PRIMARY KEY state_key (state)
) ENGINE=InnoDB;

INSERT INTO state VALUES ('D', "Deleted player");
INSERT INTO state VALUES ('N', "Normal player");