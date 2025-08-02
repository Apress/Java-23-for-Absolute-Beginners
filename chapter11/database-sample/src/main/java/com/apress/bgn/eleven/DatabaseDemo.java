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

import static java.lang.System.out;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class DatabaseDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDemo.class);
    void main(){
        // 9
        var drivers = DriverManager.drivers()
                .map(d -> d.getClass().getCanonicalName())
                .peek(out::println)
                .toList();


        drivers.forEach(driver -> {
            out.println("------ " + driver + " -----");
            if(driver.contains("mysql")) {
                try {
                    var con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/mysql",
                            "root", "mypass");
                    var stmt = con.createStatement();
                    var rs = stmt.executeQuery("select * from user");
                    while (rs.next()) {
                        //out.println(rs.getString(1)  + ":" + rs.getString(2));
                        out.println(rs.getString("Host")  + ":" + rs.getString("User"));
                    }
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var con = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "mypass");
                    var stmt = con.createStatement();
                    var rs = stmt.executeQuery("select * from user");
                    while (rs.next()) {
                        out.println(rs.getString(1));
                    }
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            // TODO
        });
    }
}
