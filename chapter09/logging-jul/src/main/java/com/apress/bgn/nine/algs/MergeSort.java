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
package com.apress.bgn.nine.algs;

import java.util.logging.Logger;

/**
 * The {@code MergeSort} class contains a single method that is a concrete implementation of {@link IntSorter#sort(int[], int, int)}.<p>
 * Instances of this class can be used to sort an {@code int[]} array using the merge-sort algorithm.
 *
 * Created by iuliana.cosmina on 30/06/2024
 * since 1.0
 * @see IntSorter
 */
public class MergeSort implements IntSorter {
    private static final Logger log = Logger.getLogger(MergeSort.class.getName());

    /**
     * Sorts{@code arr} using the MergeSort algorithm.<p>
     * <a href="https://youtu.be/XaqR3G_NVoo" target="_blank"> How it works</a>
     */
    public void sort(int[] arr, int low, int high) {
        var sb = new StringBuilder("Call sort of ")
                .append("[low,high]: [")
                .append(low).append(" ").append(high)
                .append("] ");
        for (var i = low; i <= high; ++i) {
            sb.append(arr[i]).append(" ");
        }
        log.info(sb.toString());

        if (low < high) {

            var middle = (low + high) / 2;

            //sort lower half of the interval
            sort(arr, low, middle);
            //sort upper half of the interval
            sort(arr, middle + 1, high);

            // merge the two intervals
            merge(arr, low, high, middle);
        }
    }

    private void merge(int[] arr, int low, int high, int middle) {
        var leftLength = middle - low + 1;
        var rightLength = high - middle;

        var left = new int[leftLength];
        var right = new int[rightLength];

        for (int i = 0; i < leftLength; ++i) {
            left[i] = arr[low + i];
        }
        for (int i = 0; i < rightLength; ++i) {
            right[i] = arr[middle + 1 + i];
        }

        var i = 0;
        var j = 0;

        var k = low;
        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                arr[k] = left[i++];
            } else {
                arr[k] = right[j++];
            }
            k++;
        }

        while (i < leftLength) {
            arr[k++] = left[i++];
        }

        while (j < rightLength) {
            arr[k++] = right[j++];
        }
        var sb = new StringBuilder("Called merge of [low, high, middle]: [")
                .append(low).append(" ").append(high).append(" ").append(middle)
                .append("]) ");
        for (var z = low; z <= high; ++z) {
            sb.append(arr[z]).append(" ");
        }
        log.info(sb.toString());
    }
}

