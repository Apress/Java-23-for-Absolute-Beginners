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
import java.nio.file.*;

/**
 * @author Iuliana Cosmina
 * since 1.0
 * Observation: Make sure the working directory is set to /[workspace]/java-23-for-absolute-beginners for this Launcher
 */
public class PathDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathDemo.class);
  
    void main(){
        LOGGER.info("---------------------Listing 11-8---------------------");
        File file = new File(
                "/Users/iuliana/apress/workspace/java-23-for-absolute-beginners/README.adoc");
        Path path = Path.of(file.toURI());
        LOGGER.info(path.toString());

        LOGGER.info("---------------------Listing 11-9---------------------");
        Path composedPath = Paths.get("/Users/iuliana/apress/workspace",
                "java-23-for-absolute-beginners",
                "README.adoc");
        LOGGER.info(composedPath.toString());

        LOGGER.info("---------------------path.compareTo(..)---------------------");
        LOGGER.info("Is the same path? : {} ", path.compareTo(composedPath) ==0 ? "yes" : "no");

        LOGGER.info("---------------------Listing 11-10---------------------");
        var chapterPath = Paths.get("/Users/iuliana/apress/workspace",
                "java-23-for-absolute-beginners/chapter11");
        Path filePath = chapterPath.resolve(
                "read-write-file/src/main/resources/input/data.txt");
        LOGGER.info("Resolved Path :{}", filePath.toAbsolutePath());

        LOGGER.info("---------------------Listing 11-11---------------------");
        var outputPath = FileSystems.getDefault()
                .getPath("/Users/iuliana/apress/workspace/" +
                        "java-23-for-absolute-beginners/chapter11/read-write-file/src/main/resources/output/sample");
        try {
            Path dirPath = Files.createDirectory(outputPath);
            printPathStats(dirPath);
        } catch (FileAlreadyExistsException faee) {
            LOGGER.error("Directory already exists.", faee);
        } catch (IOException e) {
            LOGGER.error("Could not create directory.", e);
        }

        LOGGER.info("---------------------Listing 11-12---------------------");
        Path copyFilePath = Paths.get(outputPath.toAbsolutePath().toString(), "data.adoc");
        try {
            Files.copy(filePath, copyFilePath);
            LOGGER.info("Exists? : {}", Files.exists(copyFilePath)? "yes": "no");
            LOGGER.info("File copied to: {}", copyFilePath.toAbsolutePath());
        } catch (FileAlreadyExistsException faee) {
            LOGGER.error("File already exists.", faee);
        } catch (IOException e) {
            LOGGER.error("Could not copy file.", e);
        }
        Path movedFilePath = Paths.get(outputPath.toAbsolutePath().toString(), "copy-data.adoc");
        try {
            Files.move(copyFilePath, movedFilePath);
            LOGGER.info("File moved to: {}", movedFilePath.toAbsolutePath());
            Files.deleteIfExists(copyFilePath);
        } catch (FileAlreadyExistsException faee) {
            LOGGER.error("File already exists.", faee);
        }  catch (IOException e) {
            LOGGER.error("Could not move file.", e);
        }     
    }

    private static void printPathDetails(Path path) {
        LOGGER.info("Path Details:");
        LOGGER.info("\tFileName : {}", path.getFileName());
        LOGGER.info("\tParent :{}", path.getParent());
        LOGGER.info("\tLocation :{}", path.toAbsolutePath());
        LOGGER.info("\tIs Absolute? : {}", path.isAbsolute());
        LOGGER.info("\tRoot :{}", path.getRoot());
        LOGGER.info("\tFileSystem : {}", path.getFileSystem());
        LOGGER.info("\tIsFileReadOnly : {}", path.getFileSystem().isReadOnly());
    }

    private static void printPathStats(Path path) {
        if (Files.exists(path)) {
            LOGGER.info("Path Details:");
            LOGGER.info("\tType: {}", Files.isDirectory(path) ? "yes" : "no");
            LOGGER.info("\tType: {}", Files.isRegularFile(path) ? "yes" : "no");
            LOGGER.info("\tType: {}", Files.isSymbolicLink(path) ? "yes" : "no");
            LOGGER.info("\tLocation :{}", path.toAbsolutePath());
            LOGGER.info("\tParent :{}", path.getParent());
            LOGGER.info("\tName : {}", path.getFileName());

            try {
                double kilobytes = Files.size(path) / (double)1024;
                LOGGER.info("\tSize : {} ", kilobytes);
                LOGGER.info("\tIs Hidden: {}", Files.isHidden(path) ? "yes" : "no");
            } catch (IOException e) {
                LOGGER.error("Could not access file.", e);
            }
            LOGGER.info("\tIs Readable: {}", Files.isReadable(path) ? "yes" : "no");
            LOGGER.info("\tIs Writable: {}", Files.isWritable(path) ? "yes" : "no");

        }
    }
}
