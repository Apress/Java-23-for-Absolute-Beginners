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
import java.util.List;
import java.util.function.Consumer;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 23/06/2024
 */
public class IntegerStreamDemo {
    void main() {
        List<Integer> bigList = List.of(50, 10, 250, 100, 23, 45, 33, 55, 67, 83, 90, 92, 94, 74, 200, 40052, 3467, 125);

        out.println("Collapsed consumer: Lambda expression");
        bigList.stream().forEach(i -> out.println(Thread.currentThread().getName() +": " + i));

        out.println("Expanded consumer: anonymous class");
        bigList.stream().forEach(
                new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        out.println(Thread.currentThread().getName() +": " + i);
                    }
                });

        out.println("Using a parallel stream");
        bigList.parallelStream().forEach(i -> out.println(Thread.currentThread().getName() +": " + i));
    }
}
