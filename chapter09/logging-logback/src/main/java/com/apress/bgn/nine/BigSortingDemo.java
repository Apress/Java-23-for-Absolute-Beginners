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
package com.apress.bgn.nine;

import com.apress.bgn.algs.MergeSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

/**
 * Class <code>BigSortingDemo</code> is an entry point of this application.
 * This class should be run to test monitoring Java Tools like: <code>jps, jcmd, jconsole</code> <p>
 * @author Iuliana Cosmina
 * since 1.0
 */
public class BigSortingDemo {
    private static final Logger log = LoggerFactory.getLogger(BigSortingDemo.class);
    static RandomGenerator randomGenerator = RandomGenerator.of("SecureRandom");

    void main() throws InterruptedException {
        Thread.sleep(3000);

        // We are using this stream of ints to generate a huge array and a huge log file. Be patient, the execution will take a while.
        var intStream = IntStream.generate(() -> randomGenerator.nextInt(350) + 1).limit(100_000_000);

        int[] arr =  intStream.toArray();

        if (log.isDebugEnabled()) {
            final StringBuilder sb = new StringBuilder("Sorting  an array with merge sort: ");
            Arrays.stream(arr).forEach(i -> sb.append(i).append(" "));
            log.debug(sb.toString());
        }

        Thread.sleep(3000);

        var mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length - 1);


        if (log.isInfoEnabled()) {
            final StringBuilder sb2 = new StringBuilder("Sorted: ");
            Arrays.stream(arr).forEach(i -> sb2.append(i).append(" "));
            log.info(sb2.toString());
        }
    }
}
