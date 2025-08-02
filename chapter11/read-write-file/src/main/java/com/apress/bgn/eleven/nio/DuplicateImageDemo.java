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
package com.apress.bgn.eleven.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class DuplicateImageDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(DuplicateImageDemo.class);

    void main(){
        LOGGER.info("---------------------Listing 11-31---------------------");
        LOGGER.info("-- Image duplicated using FileChannel -- ");
        final String inDir = "chapter11/read-write-file/src/main/resources/input/";
        final String outDir = "chapter11/read-write-file/src/main/resources/output/";
        try(FileChannel source = new RandomAccessFile(inDir + "the-beach.jpg", "r").getChannel();
            FileChannel dest = new RandomAccessFile(outDir + "copy-the-beach.jpg", "rw").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(48);
            while (source.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    dest.write(buffer);
                }
                buffer.clear();
            }
        } catch (Exception e) {
            LOGGER.debug("Image could not be copied! ", e);
        }

        LOGGER.info("---------------------Listing 11-32---------------------");
        LOGGER.info("-- Image duplicated using ReadableByteChannel/WritableByteChannel -- ");
        try(ReadableByteChannel source = new FileInputStream(inDir + "the-beach.jpg").getChannel();
            WritableByteChannel dest = new FileOutputStream(outDir + "2nd-copy-the-beach.jpg").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(48);
            while (source.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    dest.write(buffer);
                }
                buffer.clear();
            }
        } catch (Exception e) {
            LOGGER.error("Image could not be copied! ", e);
        }
    }
}
