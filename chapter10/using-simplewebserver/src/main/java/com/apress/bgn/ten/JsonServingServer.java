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

import com.sun.net.httpserver.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

import static com.apress.bgn.ten.ContextServer.SERVER_DATA;
import static com.apress.bgn.ten.MostSimpleServer.SERVER_ADDR;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class JsonServingServer {

    public static Predicate<Request> IS_GET = r -> r.getRequestMethod().equals("GET");

    void main() throws IOException {
        var serverDataLocation = new File(SERVER_DATA);

        var jsonHandler = HttpHandlers.of(200,
                Headers.of("Content-Type", "application/json"),
                Files.readString(Path.of(serverDataLocation.getAbsolutePath() + File.separator + "simple.json")));

        var methodNotAllowedHandler = HttpHandlers.of(405,
                Headers.of("Allow", "GET"), "");

        var handler = HttpHandlers.handleOrElse(IS_GET, jsonHandler, methodNotAllowedHandler);
        var server = HttpServer.create(SERVER_ADDR, 10, "/simple/", handler);

        server.start();
        out.println("Press any key to exit!");
        in.read();
        server.stop(2);
    }
}
