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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.err;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    void main(){
        LOGGER.info("-------------------Listing 11-1-----------------------");
        var file = new File("/Users/iuliana/apress/workspace/java-23-for-absolute-beginners/README.adoc");
        printFileStats(file);

        LOGGER.info("-------------------Listing 11-2-----------------------");
        try {
            var localUri = new URI("file:///Users/iuliana/apress/workspace/java-23-for-absolute-beginners/README.adoc");
            var localFile =  new File (localUri);
            printFileStats(localFile);
        } catch (URISyntaxException use) {
            LOGGER.error("Malformed URI, no file there", use);
        }

        LOGGER.info("-------------------Listing 11-3-----------------------");
        file = new File("/Users/iuliana/.gitconfig");
        printFileStats(file);

        LOGGER.info("-------------------Handling Directories: list()-----------------------");
        var d = new File("/Users/iuliana/apress/workspace/java-23-for-absolute-beginners");
        Arrays.stream(Objects.requireNonNull(d.list())).forEach(ff -> LOGGER.info("\t File Name : {}", ff));

        LOGGER.info("-------------------Handling Directories: listFiles()-----------------------");
        Arrays.stream(Objects.requireNonNull(d.listFiles())).forEach(ff -> LOGGER.info("\t File : {}", ff.getAbsolutePath()));
        LOGGER.info("------------------------------------------");
        Arrays.stream(Objects.requireNonNull(d.listFiles(childFile -> childFile.isDirectory() && childFile.getName().startsWith("chapter"))))
                .forEach(ff -> LOGGER.info("Chapter Source : {}", ff.getName()));

        LOGGER.info("-------------------Listing 11-4-----------------------");
        Arrays.stream(Objects.requireNonNull(d.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("chapter");
            }
        }))).forEach(ff -> LOGGER.info("\t Chapter directory : {}", ff.getAbsolutePath()));

        LOGGER.info("-------------------Listing 11-5-----------------------");
        File created = new File(
                "/Users/iuliana/apress/workspace/java-23-for-absolute-beginners/chapter11/read-write-file/src/main/resources/output/created.txt");
        if (!created.exists()) {
            try {
                var result = created.createNewFile();
                LOGGER.info("File was created ? {}", result);
            } catch (IOException e) {
                LOGGER.error("Could not create file.", e);
            }
        }

        LOGGER.info("-------------------Listing 11-6-----------------------");
        try {
            var temp = File.createTempFile("java_bgn_", ".tmp");
            LOGGER.info("File created at: {}", temp.getAbsolutePath());
            temp.deleteOnExit();
        } catch (IOException e) {
            LOGGER.error("Could not create temporary file.", e);
        }

        LOGGER.info("-------------------Listing 11-7-----------------------");
        file = new File(
                "chapter11/read-write-file/src/main/resources/output/created.txt");
        var renamed = new File(
                "chapter11/read-write-file/src/main/resources/output/renamed.txt");
        boolean result = file.renameTo(renamed);
        LOGGER.info("Renaming succeeded? : {} ", result);
    }

    private static void printFileStats(File f) {
        if (f.exists()) {
            LOGGER.info("File Details:");
            LOGGER.info("\tName : {}", f.getName());
            LOGGER.info("\tParent :{}", f.getParent());
            LOGGER.info("\tType : {}", f.isFile() ? "file" : "directory or symlink");
            LOGGER.info("\tLocation :{}", f.getAbsolutePath());

            double kilobytes = f.length() / (double)1024;
            LOGGER.info("\tSize : {} ", kilobytes);

            LOGGER.info("\tIs Hidden : {}", f.isHidden());
            LOGGER.info("\tIs Readable? : {}", f.canRead());
            LOGGER.info("\tIs Writable? : {}", f.canWrite());
        } else {
            err.println("File does not exist!");
        }
    }
}
