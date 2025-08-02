create database musicdb ENCODING 'UTF8';

CREATE USER sample WITH ENCRYPTED PASSWORD 'sample';

GRANT CONNECT ON DATABASE musicdb TO sample;
GRANT ALL PRIVILEGES ON DATABASE musicdb TO sample;

-- switch connection to sample here
\connect musicdb

CREATE SCHEMA IF NOT EXISTS AUTHORIZATION sample;

CREATE TABLE IF NOT EXISTS sample.SINGERS (
                                 ID SERIAL PRIMARY KEY,
                                 NAME VARCHAR(30) NOT NULL,
                                 RATING DOUBLE PRECISION,
                                 BIRTH_DATE DATE
);

GRANT ALL PRIVILEGES ON  ALL TABLES IN SCHEMA  sample TO sample;
GRANT ALL PRIVILEGES ON  ALL SEQUENCES IN SCHEMA  sample TO sample;

-- SELECT datname FROM pg_database;