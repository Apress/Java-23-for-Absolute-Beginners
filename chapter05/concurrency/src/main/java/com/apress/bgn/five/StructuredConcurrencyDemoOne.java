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

import com.apress.bgn.four.classes.DataGenerator;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.StructuredTaskScope.*;
import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 05/05/2024
 */
public class StructuredConcurrencyDemoOne {
    void main() {
        var start = Instant.now();
        try (var scope = new ShutdownOnSuccess<Integer>()) {
            Subtask<Integer> task1 = scope.fork(() -> DataGenerator.genInt());
            Subtask<Integer> task2 =  scope.fork(() -> 2);

            scope.join();
            out.println("task1: " + task1.state() + ", result : " + (task1.state() == Subtask.State.SUCCESS ? task1.get() : "Not Available")  +
                        "\ntask2: " + task2.state()+ ",  result : " + (task2.state() == Subtask.State.SUCCESS ? task2.get() : "Not Available")
                    );
            out.println("Execution time : " + Duration.between(start, Instant.now()).toMillis() + " ms");
            out.println("Task result: " + scope.result());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
