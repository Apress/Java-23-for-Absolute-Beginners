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

import com.apress.bgn.eight.util.Song;
import com.apress.bgn.eight.util.StreamMediaLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 27/06/2024
 */
public class LongestSongFactoryDemo {
    void main(){
        var songs = StreamMediaLoader.loadSongs();
        var longestDuration = songs
                //.gather(new LongestSong(360))
                .gather(LONGEST_SONG(360))
                .findFirst().orElse(-1);
        out.println("Longest duration: " + longestDuration + " seconds");
    }

    static Gatherer<Song, List<Integer>, Integer>  LONGEST_SONG(int limit) {
        return Gatherer.of(
                () -> new ArrayList<>(1),
                Gatherer.Integrator.of(
                        (max, element, downstream) -> {
                    if (max.isEmpty()) max.addFirst(element.getDuration());
                    else if (element.getDuration() > max.getFirst()) max.set(0, element.getDuration());

                    if (element.getDuration() >= limit) {
                        downstream.push(element.getDuration());
                        return false;
                    }
                    return true;
                }),
                (left, right) -> {
                    // If either the "left" or "right" list contains
                    // no value, then return the other
                    if (left.isEmpty()) return right;
                    if (right.isEmpty()) return left;

                    // Return the list that contains the larger integer
                    int leftVal = left.getFirst();
                    int rightVal = right.getFirst();
                    if (leftVal > rightVal) return left;
                    else return right;
                },
                (max, downstream) -> {
                    if (!max.isEmpty()) {
                        downstream.push(max.getFirst());
                    }
                }
        );
    }


    record LongestSong(int limit) implements Gatherer<Song, List<Integer>, Integer> {
        @Override
        public Supplier<List<Integer>> initializer() {
            return () -> new ArrayList<>(1);
        }

        @Override
        public Integrator<List<Integer>, Song, Integer> integrator() {
            return Integrator.of((max, element, downstream) -> {
                if (max.isEmpty()) max.addFirst(element.getDuration());
                else if (element.getDuration() > max.getFirst()) max.set(0, element.getDuration());

                // If the duration is equal or greater to the limit,
                // "short-circuit": emit the current song downstream
                // and return false to stop processing stream elements
                if (element.getDuration() >= limit) {
                    downstream.push(element.getDuration());
                    return false;
                }

                // Return true to continue processing stream elements
                return true;
            });
        }

        @Override
        public BinaryOperator<List<Integer>> combiner() {
            return (left, right) -> {
                // If either the "left" or "right" list contains
                // no value, then return the other
                if (left.isEmpty()) return right;
                if (right.isEmpty()) return left;

                // Return the list that contains the larger integer
                int leftVal = left.getFirst();
                int rightVal = right.getFirst();
                if (leftVal > rightVal) return left;
                else return right;
            };
        }

        @Override
        public BiConsumer<List<Integer>, Downstream<? super Integer>> finisher() {
            // Emit the largest integer, if there is one, downstream
            return (max, downstream) -> {
                if (!max.isEmpty()) {
                    downstream.push(max.getFirst());
                }
            };
        }
    }
}
