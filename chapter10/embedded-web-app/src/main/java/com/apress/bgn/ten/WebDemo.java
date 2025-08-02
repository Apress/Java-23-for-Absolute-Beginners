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

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class WebDemo {

    private static final Logger LOGGER = Logger.getLogger(WebDemo.class.getName());

    public static final Integer PORT = Optional.ofNullable(System.getenv("PORT")).map(Integer::parseInt).orElse(8080);
    public static final String TMP_DIR = Optional.ofNullable(System.getenv("TMP_DIR")).orElse("/tmp/tomcat-tmp");
    public static final String STATIC_DIR = Optional.ofNullable(System.getenv("STATIC_DIR")).orElse("/tmp/tomcat-static");

    void main() throws LifecycleException, IOException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(TMP_DIR);
        tomcat.setPort(PORT);
        // create a default Connector if there is none and adds it to the service
        tomcat.getConnector();
        // prevent register jsp servlet
        tomcat.setAddDefaultWebXmlToWebapp(false);

        String contextPath = ""; // root context
        boolean createDirs =  new File(STATIC_DIR).mkdirs();
        if(createDirs) {
            LOGGER.info("Tomcat static directory created successfully.");
        } else {
            LOGGER.severe("Tomcat static directory could not be created.");
        }
        String docBase = new File(STATIC_DIR).getCanonicalPath();
        Context context = tomcat.addWebapp(contextPath, docBase);

        addIndexServlet(tomcat, contextPath, context); // omitted

        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // <1>
            try {
                tomcat.getServer().stop();
            } catch (LifecycleException e) {
                LOGGER.warning("Unable to stop Tomcat server.");
            }
        }));

        // Needed before Servlet 3.0
        /*SampleServlet sampleServlet = new SampleServlet();
        tomcat.addServlet(contextPath, sampleServlet.getServletName(), sampleServlet);
        context.addServletMappingDecoded(sampleServlet.getUrlPattern(), sampleServlet.getServletName());*/

        tomcat.start();
        tomcat.getServer().await();
    }

    private static void addIndexServlet(Tomcat tomcat, String contextPath, Context context) {
        var indexServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws IOException {
                var writer = resp.getWriter();
                writer.print("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
                        </head>
                        <body style="background-color:black">
                        <!-- NAVBAR -->
                        <nav class="navbar navbar-expand navbar-dark bg-primary">
                            <div class="container-fluid">
                                <a class="navbar-brand" href="./">
                                    Java 23
                                </a>
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                    <!-- NAVBAR LINKS -->
                                    <li class="nav-item">
                                        <a class="nav-link active" href="#">About the Author</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Contact</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3/dist/js/bootstrap.bundle.min.js"></script>
                        </body>
                        <div>
                            <h1 style="color:#ffd200"> Embedded Tomcat 11.0.0-M22 says hi!</h1>
                        </div>
                        </html>
                        """);
            }
        };
        var servletName = "IndexServlet";
        var urlPattern = "/";
        tomcat.addServlet(contextPath, servletName, indexServlet);
        context.addServletMappingDecoded(urlPattern, servletName);
    }
}
