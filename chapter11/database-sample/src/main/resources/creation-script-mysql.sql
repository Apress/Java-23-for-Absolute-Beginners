create database musicdb;

-- creating users
CREATE USER 'sample'@'%' IDENTIFIED BY 'sample';


-- if your blueprint sets the password it can be removed here
-- grant all privileges on *.*  to 'sample'@'%' identified by 'sample';
--  useful if sockets work also
GRANT ALL PRIVILEGES ON *.* TO 'sample'@'%';

flush privileges;

-- and now the table for musicdb
use musicdb;

CREATE TABLE SINGERS (
                          ID BIGINT NOT NULL AUTO_INCREMENT,
                          NAME VARCHAR(30) NOT NULL,
                          RATING DOUBLE,
                          BIRTH_DATE DATETIME,
                          PRIMARY KEY (ID)
);
