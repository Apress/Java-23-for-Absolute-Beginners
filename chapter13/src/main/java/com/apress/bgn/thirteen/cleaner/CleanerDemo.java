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
package com.apress.bgn.thirteen.cleaner;

import com.apress.bgn.thirteen.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.apress.bgn.thirteen.util.MemAudit.printBusyMemory;
import static com.apress.bgn.thirteen.util.MemAudit.printTotalMemory;

import java.lang.ref.Cleaner;
import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * @author iuliana.cosmina on 28/08/2024
 */
public class CleanerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanerDemo.class);
    public static final Cleaner cleaner = Cleaner.create();
    private static NameGenerator nameGenerator = new NameGenerator();

    public static void main(String... args) {
        printTotalMemory(LOGGER);
        int count = 0;
        for (int i = 0; i < 100_000; ++i) {
            genActor();
            count++;
            if (count % 1000 == 0) {
                printBusyMemory(LOGGER);
                System.gc();
            }
        }

        //filling memory with arrays of String to force GC to clean up Actor objects
        IntStream.range(0, 10_000).forEach(_ -> {
            var s = new String[10_000];
            try {Thread.sleep(1);} catch (InterruptedException _) {}
        });

    }

    private static Cleaner.Cleanable genActor() {
        Actor a = new Actor(nameGenerator.genName(), LocalDate.now());
        LOGGER.info("JVM created: {}", a.getName());
        Cleaner.Cleanable handle = cleaner.register(a, new ActorRunnable(a.getName(), LOGGER));
        return handle;
    }

    static class ActorRunnable implements Runnable {
        private final String actorName;
        private final Logger log;

        public ActorRunnable(String actorName, Logger log) {
            this.actorName = actorName;
            this.log = log;
        }

        @Override
        public void run() {
            log.info("GC Destroyed: {} ", actorName);
        }
    }
}
