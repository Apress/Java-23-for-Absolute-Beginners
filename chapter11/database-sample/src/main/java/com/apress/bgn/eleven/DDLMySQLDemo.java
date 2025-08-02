/*
Freeware License, some rights reserved

Copyright (c) 2024 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.eleven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author iuliana.cosmina on 07/08/2024
 */
public class DDLMySQLDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(DDLMySQLDemo.class);

    void main(){
        try (var con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mysql",
                "root", "mypass")) {
            var stmt = con.createStatement();
            stmt.execute("create database musicdb");
            stmt.execute("CREATE USER 'sample'@'%' IDENTIFIED BY 'sample'");
            stmt.execute("GRANT ALL PRIVILEGES ON *.* TO 'sample'@'%'");
            stmt.execute("flush privileges");

            var rs = stmt.executeQuery("SELECT * FROM user");
            var foundUser = false;
            while (rs.next() && !foundUser) {
                foundUser = rs.getString("User") .equals("sample");
            }
            if (foundUser) {
                LOGGER.info("User 'sample' created.");
                LOGGER.info("Creating table 'SINGERS' ...");
                stmt.execute("use musicdb");
                var stmtResult = stmt.execute("""
                    CREATE TABLE SINGERS (
                          ID BIGINT NOT NULL AUTO_INCREMENT,
                          NAME VARCHAR(30) NOT NULL,
                          RATING DOUBLE,
                          BIRTH_DATE DATETIME,
                          PRIMARY KEY (ID)
                    );
                """);
                if (!stmtResult) {
                    LOGGER.info("Created 'SINGERS' table in database 'musicdb.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Well, this is unexpected...", e);
        }
    }
}
