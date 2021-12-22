-- -----------------------------------------------------
-- Schema avarum_game
-- -----------------------------------------------------
CREATE EXTENSION IF NOT EXISTS hstore schema avarum_game;
DROP SCHEMA IF EXISTS avarum_game CASCADE;
DROP TABLE IF EXISTS avarum_game.player CASCADE;
DROP TABLE IF EXISTS avarum_game.enemy CASCADE;


CREATE SCHEMA avarum_game;
SHOW search_path;
SET search_path  TO "$user", public, avarum_game;


-- -----------------------------------------------------
-- Table avarum_game.player
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS avarum_game.player (
  id BIGSERIAL PRIMARY KEY NOT NULL,
    potion_chance SMALLINT NOT NULL,
    potion_quantity SMALLINT NOT NULL,
    potion_heal int[] NOT NULL,
    userid BIGINT NOT NULL,
    CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES avarum_users.user (id));

-- -----------------------------------------------------
-- Table avarum_game.enemy
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS avarum_game.enemy (
  id BIGSERIAL PRIMARY KEY NOT NULL,
    type VARCHAR(20) NOT NULL,
    ability_scores hstore NOT NULL,
    stats hstore NOT NULL,
    pattern_length SMALLINT NOT NULL);

-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------
INSERT INTO avarum_game.player (potion_chance, potion_quantity, potion_heal, userid)
VALUES (60, 1, '{30, 60}', 3);
