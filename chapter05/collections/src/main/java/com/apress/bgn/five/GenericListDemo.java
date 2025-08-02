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
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 29/04/2024
 */
public class GenericListDemo {
    void main() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        out.println("-------- fori ----------"); // imperative
        for (int i = 0; i < list.size(); i++) {
            out.print(list.get(i) + " ");
        }

        // JDK 5
        out.println("\n--------- enhanced for ---------"); // imperative
        for (var str : list) {
            out.print(str +" ");
        }

        out.println("\n-------- Iterator ----------"); // imperative using iterator
        for (var it = list.iterator(); it.hasNext();) {
            out.print(it.next()+ " ");
        }

        // imperative, can traverse it starting at the end, get access index, can remove and add elements
        out.println("\n-------- ListIterator ----------");
        for (var it = list.listIterator(); it.hasNext();) {
            out.print(it.next()+ " ");
        }

        // JDK 1.8
        out.println("\n--------- forEach ---------"); // most simple functional traversal
        list.forEach(s -> out.print(s +" "));

        // functional, converts to stream before traversal;
        // advantage: possibility to stream operations like filter, map, distinct, etc.
        out.println("\n--------- stream().forEach ---------");
        list.stream().forEach(s -> out.print(s +" "));

        // JDK 1.8
        // functional, using a range to generate indexes for the traversal
        out.println("\n-------- range ----------");
        IntStream.range(0, list.size())
                .forEach(i -> out.print(list.get(i) +" "));

        // functional, using a rangeClosed() to generate indexes for the traversal
        out.println("\n-------- rangeClosed ----------");
        IntStream.rangeClosed(0, list.size()-1)
                .forEach(i -> out.print(list.get(i) +" "));

        // all new in JDK 21
        var reversed = list.reversed();
        out.println("[after reversed()]:" +reversed);
        out.println("---------------------------------------------------");

        out.println("[first element]: " + list.getFirst()); // list.get(0)
        out.println("[last element]: " + list.getLast());   // list.get(list.size()-1)
        out.println("---------------------------------------------------");

        out.println("[original list]: " + list);
        list.addFirst("zero");                        // list.add(0, "zero");
        out.println("[after addFirst(..)]: " + list);
        list.removeFirst();                             // list.remove(0);
        out.println("[after removeFirst(..)]: " + list);
        out.println("---------------------------------------------------");

        list.addLast("four");                       // list.add("four");
        out.println("[after addLast(..)]: " + list);
        list.removeLast();                             //list.remove(list.size()-1);
        out.println("[after removeLast(..)]" +list);
    }

}
