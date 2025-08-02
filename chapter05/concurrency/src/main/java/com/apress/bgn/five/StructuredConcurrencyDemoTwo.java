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

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static com.apress.bgn.five.VirtualThreadsExecutorDemo.RND;

import static java.util.concurrent.StructuredTaskScope.*;
import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 06/05/2024
 */
public class StructuredConcurrencyDemoTwo {

    void main() {
        var d = new StructuredConcurrencyDemoTwo();
        List<Subtask<Integer>> subtasks = new ArrayList<>();
        var start = Instant.now();
        try (var scope = new ShutdownOnFailure()) {
            for (int i = 0; i < 10; i++) {
                subtasks.add(scope.fork(() -> d.genValue()));
            }

            scope.joinUntil(Instant.now().plusSeconds(5));
            scope.throwIfFailed(t -> new IllegalStateException("Well this sucks!  " + t.getMessage()));
            // if all subtasks complete successfully
            var result = subtasks.stream()
                    .map(Subtask::get)
                    .collect(Collectors.toList());
            out.println("All results: " + result);
        } catch (InterruptedException | TimeoutException | IllegalStateException e) {
            System.err.println("Some tasks have failed. " + e.getMessage());
            subtasks.forEach(t -> out.println(
                        "task "  + subtasks.indexOf(t)  + " " + t.state() + ", result : " + (t.state() == Subtask.State.SUCCESS ? t.get() : "Not Available")
                    )
            );
        }
        out.println("Execution time : " + Duration.between(start, Instant.now()).toMillis() + " ms");
    }

    Integer genValue() throws InterruptedException {
        var generatedVal = RND.nextInt();
        if(generatedVal % 11 ==0) {
            throw new IllegalStateException("This value is bad, bad bad...");
        }
        return generatedVal;
    }

}