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
package com.apress.bgn.eight;

import java.util.function.Function;
import java.util.random.RandomGenerator;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 28/06/2024
 */
public class BuiltInGatherersDemo {
    static RandomGenerator randomGenerator = RandomGenerator.of("SecureRandom");
    void main() {
        out.println(" -- windowFixed  --");
        Stream.generate(() ->  randomGenerator.nextInt(300) + 1)
                .gather(Gatherers.windowFixed(3))
                .limit(5).forEach(e ->out.println(e));

        out.println(" -- windowSliding  --");
        Stream.generate(() ->  randomGenerator.nextInt(300) + 1)
                .gather(Gatherers.windowSliding(3))
                .limit(5).forEach(e ->out.println(e));

        out.println(" -- fold  --");
        Stream.of(1,2,3,4,5,6,7,8,9)
                .gather(
                        Gatherers.fold(
                                () -> "",
                                (result, element) -> {
                                    if (result.equals("")) return element.toString();
                                    return result + ";" +element;
                                }
                        )
                )
                .findFirst().ifPresent(out::println);

        out.println(" -- scan  --");
        Stream.of(0,1,1,2,3,5,8,13,21,34,55,89,144).
                gather(Gatherers.scan(() -> 0, Integer::sum))
                .forEach(de -> out.print(de +" "));

        out.println("\n-- mapConcurrent --");
        Function<Integer, Integer> triple = integer -> {
            out.println("Thread.currentThread()} - " + integer);
            return integer * 3;
        };

        Stream.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25)
                .gather(Gatherers.mapConcurrent(5, triple))
                .limit(10)
                .forEach(de -> out.print(de + " "));
    }
}
