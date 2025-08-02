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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliana Cosmina
 * since 1.0
 * Observation: Make sure the working directory is set to /[workspace]/java-23-for-absolute-beginners for this Launcher
 */
public class FilesReadDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesReadDemo.class);
    void main() throws IOException {
        LOGGER.info("---------------------Listing 11-14---------------------");
        final String inDir = "chapter11/read-write-file/src/main/resources/input/";
        var file= new File(inDir + "data.txt");

        try {
            var content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            LOGGER.info("Read with Files.readAllBytes --> {}", content);
        } catch (IOException e) {
            LOGGER.info("Something went wrong! ", e);
        }

        LOGGER.info("---------------------Listing 11-15---------------------");
        try {
            List<String> lyricList = Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8);
            lyricList.forEach(System.out::println);
        } catch (IOException e) {
            LOGGER.info("Something went wrong! ", e);
        }

        LOGGER.info("---------------------Listing 11-16---------------------");
        try {
            var content = Files.readString(Paths.get(file.toURI()), StandardCharsets.UTF_8);
            LOGGER.info("Read with Files.readString --> {}", content);
        } catch (IOException e) {
            LOGGER.info("Something went wrong! ", e);
        }

        LOGGER.info("---------------------Files.readAllLines(..)---------------------");
        List<String> dataList = Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8)
                .stream()
                .filter(line -> line!= null && !line.isBlank())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        LOGGER.info("---------------------Files.lines(..)---------------------");
        dataList = Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8)
                .filter(line -> line!= null && !line.isBlank() )
                .map(String::toUpperCase)
                .toList();
    }
}
