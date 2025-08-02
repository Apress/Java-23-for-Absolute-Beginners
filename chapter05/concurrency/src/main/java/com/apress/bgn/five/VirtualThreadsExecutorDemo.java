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
package com.apress.bgn.five;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.random.RandomGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.apress.bgn.five.VirtualThreadsExecutorDemo.log;

/**
 * Created by iuliana.cosmina on 05/05/2024
 */
public class VirtualThreadsExecutorDemo {
    public static final Logger log = LoggerFactory.getLogger(VirtualThreadsExecutorDemo.class);
    public static RandomGenerator RND = RandomGenerator.of("SecureRandom");

    void main() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var tasks = new ArrayList<MyTask>();
            for (int i = 0; i < 1_000; i++) {
                tasks.add(new MyTask());
            }

            long time = System.currentTimeMillis();
            var futures = executor.invokeAll(tasks);

            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }

            time = System.currentTimeMillis() - time;
            log.info(">> Virtual threads: sum = " + sum+ ", time = " + time + " ms");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Failed to execute tasks", e);
        }
    }
}

class MyTask implements Callable<Integer> {

    @Override
    public Integer call() {
        var pause = VirtualThreadsExecutorDemo.RND.nextInt(0, 1000);
        log.info("Thread.currentThread()} produces " + pause);
        return pause;
    }
}

