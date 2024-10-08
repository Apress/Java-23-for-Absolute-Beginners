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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.nio.charset.StandardCharsets;
import static java.nio.file.StandardOpenOption.APPEND;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class FilesWritingDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesWritingDemo.class);
    void maim() {
        LOGGER.info("---------------------Listing 11-22---------------------");
        var file = new File("chapter11/read-write-file/src/main/resources/output/data.txt");

        byte[] data = "Some of us, we’re hardly ever here".getBytes();
        try {
            Path dataPath = Files.write(file.toPath(), data);
            LOGGER.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            LOGGER.debug("Could not write data to file", e);
        }

        LOGGER.info("---------------------Listing 11-23---------------------");
        try {
            Path dataPath = Files.writeString(file.toPath(),
                    "\nThe rest of us, we're born to disappear",
                    StandardCharsets.UTF_8,
                    APPEND);
            LOGGER.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("---------------------Listing 11-24---------------------");
        var dataList = List.of(
                "How do I stop myself from",
                "Being just a number?");
        try {
            Path dataPath = Files.write(file.toPath(), dataList,
                    StandardCharsets.UTF_8,
                    APPEND);
            LOGGER.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
