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
package com.apress.bgn.eleven.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Iuliana Cosmina
 * since 1.0
 * Observation: Make sure the working directory is set to /[workspace]/java-23-for-absolute-beginners for this Launcher
 */
public class ScannerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScannerDemo.class);

    void main() {
        try {
            final String inDir = "chapter11/read-write-file/src/main/resources/input/";
            //var scanner = new Scanner(new File("chapter11/read-write-file/src/main/resources/input/data.txt"));
            var scanner = new Scanner(Paths.get(new File(inDir + "data.txt").toURI()), StandardCharsets.UTF_8.name());
            var content = "";
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }
            scanner.close();
            LOGGER.info("Read with Scanner --> {}", content);
        } catch (IOException e) {
            LOGGER.error("Something went wrong! ", e);
        }
    }
}
