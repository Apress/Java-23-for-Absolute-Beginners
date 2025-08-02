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
 * @author iuliana.cosmina on 24/08/2024
 */
public class DMLMySQLDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(DMLMySQLDemo.class);
    void main() {
        try (var con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/musicdb",
                "sample", "sample")) {

            var stmt = con.createStatement();

            // create singer
            var stmtResult = stmt.executeUpdate("""
                INSERT INTO SINGERS(NAME, RATING, BIRTH_DATE)
                VALUES ('John Mayer', 4.5, '1977-10-16');
            """);
            if (stmtResult == 1) {
                LOGGER.info("Singer inserted");
            }

            // count records
            var rs = stmt.executeQuery("SELECT count(ID) as CNT from SINGERS;");
            while (rs.next()) {
                LOGGER.info("Number of records in 'SINGERS' table: {}", rs.getInt(1));
            }
            // get singer by Name
            rs = stmt.executeQuery("SELECT * FROM SINGERS WHERE NAME='John Mayer';");
            while (rs.next()) {
                LOGGER.info(
                        rs.getString("ID")  + ", "
                        + rs.getString("NAME")  + ", "
                        + rs.getDouble("RATING") + ", "
                        + rs.getDate("BIRTH_DATE"));
            }
            // update singer
            stmtResult = stmt.executeUpdate("""
                UPDATE SINGERS
                SET RATING= 5.0
                WHERE NAME='John Mayer';
            """);
            if (stmtResult == 1) { // false if it is an update count or there are no results
                LOGGER.info("Singer updated.");
            }
            // delete singer
            stmtResult = stmt.executeUpdate("""
                DELETE FROM SINGERS
                WHERE NAME='John Mayer';
            """);
            if (stmtResult == 1) { // false if it is an update count or there are no results
                LOGGER.info("Singer deleted.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
