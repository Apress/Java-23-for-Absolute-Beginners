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
package com.apress.bgn.ten;

import com.sun.net.httpserver.SimpleFileServer;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static com.apress.bgn.ten.ContextServer.SERVER_DATA;
import static com.apress.bgn.ten.MostSimpleServer.SERVER_ADDR;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ZipServingServer {
    static final Path CWD = Path.of(".").toAbsolutePath();

    void main() throws Exception {
        Path root = createZipFileSystem();
        var server = SimpleFileServer.createFileServer(SERVER_ADDR, root, SimpleFileServer.OutputLevel.VERBOSE);
        server.start();
        out.println("Press any key to exit!");
        in.read();
        server.stop(2);
    }

    /**
     * ├── simple.zip
     * │     ├── one.txt
     * │     └── two
     * │         └── two.txt
     */
    private static Path createZipFileSystem() throws Exception {
        var serverDataLocation = Path.of(new File(SERVER_DATA +  File.separator + "simple.zip").getAbsolutePath()).toAbsolutePath().normalize();

        var fs = FileSystems.newFileSystem(serverDataLocation);
        assert fs != FileSystems.getDefault();
        var root = fs.getPath("/");

        return root;
    }
}
