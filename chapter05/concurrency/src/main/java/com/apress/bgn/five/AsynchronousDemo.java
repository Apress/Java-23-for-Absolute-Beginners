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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by iuliana.cosmina on 05/05/2024
 */
public class AsynchronousDemo {

    void main() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(
                    () -> System.out.println(Thread.currentThread().getName() +" async run (1)") // Runnable as lambda
                ).get(); // Block and wait for the future to complete

        var result1 = CompletableFuture.supplyAsync(() ->  Thread.currentThread().getName() + " async run (2)" // Supplier<String> as lambda
            ).get(); // Block and wait for the future to complete
        System.out.println(result1);

        var result2 = CompletableFuture.supplyAsync(() ->  Thread.currentThread().getName()+ " async run (3)" // Supplier<String> as lambda
            ).thenApply(String::toUpperCase) // apply transformation to result
                .get(); // Block and wait for the future to complete
        System.out.println(result2);

        CompletableFuture.supplyAsync(() ->  Thread.currentThread().getName() + " async run (4)" // Supplier<String> as lambda
            ).thenAccept(System.out::println); //  Consumer<T> processes the result when received

        var result3 = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName()+ "  async run (5)" // Supplier<String> as lambda
                ).thenApplyAsync(s -> Thread.currentThread().getName() + " thenApplyAsync : " + s) // apply async transformation to result
                .get(); // Block and wait for the future to complete
        System.out.println(result3);


        var result4 = CompletableFuture.supplyAsync(() ->
                {
                    if (System.currentTimeMillis()%2 ==0) {
                        throw new IllegalStateException("No can do!");
                    }
                    return Thread.currentThread().getName() + " async run (6)";
                }
        ).exceptionally(ex -> {
                    System.err.println(ex.getMessage());
                    return "There be dragons!";
                })
                .get(); // Block and wait for the future to complete
        System.out.println(result4);

        try {
            Thread.sleep(2000); // making sure 'main' thread finishes execution last
        } catch (InterruptedException _) {}
    }
}
