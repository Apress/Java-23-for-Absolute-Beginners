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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;
/**
 * Created by iuliana.cosmina on 26/06/2024
 */
public class MoreStreamsDemo {
    void main(){
        out.println("Listing 8-34. Using  `flatMap(..)` to unwrap stream elements.");
        List<List<Integer>> testList = List.of (List.of(2,3), List.of(4,5), List.of(6,7));
        out.println(processList(testList));

        out.println("Listing 8-35. Flattening of a `Optional<Optional<T>>`.");
        // explicit flattening
        Optional<String> str0 = Optional.of("42");
        Optional<Optional<Integer>> resInt0 = str0.map(toIntOpt);
        Optional<Integer> desiredRes0 = resInt0.orElse(Optional.empty());
        out.println("finally: " + desiredRes0.get());

        // flatMap(..) flattening
        Optional<String> str1 = Optional.of("42");
        Optional<Integer> desiredRes1 = str1.flatMap(toIntOpt);
        out.println("boom: " + desiredRes1.get());

        out.println("Listing 8-36. Extracting the first element in an ordered stream.");
        // non empty stream, result 'ever'
        List<String> pieces = List.of("some","of", "us", "we’re", "hardly", "ever", "here");
        String first0 = pieces.stream().sorted().findFirst().get();
        out.println("First from sorted list: " + first0);

        // empty stream, result 'none'
        pieces = List.of();
        String first1 = pieces.stream().sorted().findFirst().orElse("none");
        out.println("First from sorted list: " + first1);

        out.println("Listing 8-37. Counting elements of a stream, after removing duplicate elements.");
        pieces = List.of("as","long", "as", "there", "is", "you", "there", "is", "me");
        long count = pieces.stream().distinct().count();
        out.println("Elements in the stream: " + count);

        out.println("Listing 8-38. Computing the maximum and minimum value in a stream.");
        Stream<Integer> ints0 = Stream.of(5,2,7,9,8,1,12,7,2);
        ints0.limit(4).min(Integer::compareTo)
                .ifPresent(min -> out.println("Min is: " + min));
        // Prints "Min is: 2"

        Stream<Integer> ints1 = Stream.of(5,2,7,9,8,1,12,7,2);
        ints1.limit(4).max(Integer::compareTo)
                .ifPresent(max -> out.println("Max is: " + max));
        // Prints "Max is: 9"
    }

    public static Function<String, Optional<Integer>> toIntOpt = str -> {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    };


    public static List<Integer> processList( List<List<Integer>> list) {
        List<Integer> result = list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return result;
    }
}
