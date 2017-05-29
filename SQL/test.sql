DROP DATABASE IF EXISTS tutorial_database;

CREATE DATABASE IF NOT EXISTS tutorial_database;

USE tutorial_database;

CREATE TABLE IF NOT EXISTS clients
(
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  title           VARCHAR(150) NOT NULL,
  body            TEXT,
  userId          INT unsigned,
  PRIMARY KEY     (id)
);

INSERT INTO clients (title, body, userId) VALUES
('title1', 'body1', 4),
('title2', 'body2', 122),
('title3', 'body3', 533);

CREATE TABLE cats
(
  id              INT unsigned NOT NULL AUTO_INCREMENT,
  name            VARCHAR(150) NOT NULL,                # Name of the cat
  owner           VARCHAR(150) NOT NULL,                # Owner of the cat
  birth           DATE NOT NULL,                        # Birthday of the cat
  PRIMARY KEY     (id)
);

INSERT INTO cats ( name, owner, birth) VALUES
  ( 'Sandy', 'Lennon', '2015-01-03' ),
  ( 'Cookie', 'Casey', '2013-11-13' ),
  ( 'Charlie', 'River', '2016-05-21' );
