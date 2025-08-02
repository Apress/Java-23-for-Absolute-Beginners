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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ReadersDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadersDemo.class);

    void main() {
        LOGGER.info("-------------------Listing 11-17-----------------------");
        BufferedReader reader = null;
        final String inDir = "chapter11/read-write-file/src/main/resources/input/";
        try {
            reader = new BufferedReader(new FileReader(new File(inDir + "data.txt"), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            LOGGER.info("Read with BufferedReader --> {}", sb.toString());
        } catch (Exception e) {
            LOGGER.error("File could not be read! ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    LOGGER.error("Something went wrong! ", ioe);
                }
            }
        }

        LOGGER.info("-------------------Listing 11-18-----------------------");
        try (BufferedReader br = new BufferedReader(new FileReader(new File(inDir + "data.txt")))){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            LOGGER.info("Read with BufferedReader --> {}", sb.toString() );
        } catch (Exception e) {
            LOGGER.info("Something went wrong! ", e);
        }

        LOGGER.info("-------------------Listing 11-19-----------------------");
        File file = new File(inDir + "data.txt");
        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            LOGGER.info("Read with BufferedReader --> {}", sb.toString() );
        } catch (Exception e) {
            LOGGER.info("Something went wrong! ", e);
        }

        LOGGER.info("-------------------Listing 11-20-----------------------");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            LOGGER.info("Read with BufferedReader(InputStreamReader(FileInputStream(..))) --> {}", sb.toString() );
        } catch (Exception e) {
            LOGGER.info("Something went wrong! ", e);
        }

    }
}
