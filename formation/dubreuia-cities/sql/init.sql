DROP TABLE IF EXISTS user_city;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS country;

CREATE TABLE country
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    language CHAR(16)     NOT NULL,
    name     VARCHAR(512) NOT NULL UNIQUE
);

CREATE TABLE region
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(512) NOT NULL UNIQUE,
    country_id INTEGER      NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE city
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(512) NOT NULL UNIQUE,
    region_id INTEGER      NOT NULL,
    FOREIGN KEY (region_id) REFERENCES region (id)
);

CREATE TABLE user
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(512) NOT NULL UNIQUE
);

CREATE TABLE user_city
(
    user_id INTEGER NOT NULL,
    city_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, city_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (city_id) REFERENCES city (id)
);

SELECT *
FROM city;
SELECT *
FROM region;
SELECT *
FROM country;
SELECT *
FROM user;
