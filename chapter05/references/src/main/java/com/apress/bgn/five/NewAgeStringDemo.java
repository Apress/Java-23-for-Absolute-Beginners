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

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 28/04/2024
 */
public class NewAgeStringDemo {

    void main() {
        var lyrics = """
                    Do not waste this evening
               \tBaby, I'm begging you!   
               Your big imagination's playing its tricks on you   
                    If you think my up and leaving's something I'm going to do,   
               \tFeel my chest when I look at you
               Baby you...    
               """;

        out.println("Original text: \n" + lyrics.strip());
        out.println("--------");

        /* Java 11 methods */
        out.println("------ Java 11 ------");
        out.println(" -- lyrics.strip() -- \n" + lyrics.strip());
        out.println(" -- lyrics.stripLeading() -- \n" + lyrics.stripLeading());
        out.println(" -- lyrics.stripTrailing() -- \n" + lyrics.stripTrailing());
        out.println(" -- lyrics.isBlank() -- \n" + lyrics.isBlank());
        out.println(" -- lyrics.lines() -- ");
        lyrics.lines().forEach(out::println);
        out.println(" -- lyrics.repeat(3) -- \n" + "la".repeat(3));

        /* Java 12 methods */
        out.println("------ Java 12 ------");
        out.println(" -- lyrics.indent(4) -- \n" + lyrics.indent(4));


        /* Java 15 methods */
        out.println("------ Java 15 ------");
        var you = "you";
        var lyrics2 = """
               |     Do not waste this evening
               |\tBaby, I'm begging %s!   
               |Your big imagination's playing its tricks on %s   
               |     If you think my up and leaving's something I'm going to do,   
               |\tFeel my chest when I look at %s
               |Baby %s...    
               """;
        out.println(" --lyrics2.formatted(you, you, you, you) -- \n" + lyrics2.formatted(you, you, you, you));

        /* Java 21 methods */
        out.println("------ Java 21 ------");
        out.println(" --  lyrics.indexOf('y', 30,40) -- \n" + lyrics.indexOf('y', 30,40));
        out.println(" --  lyrics.indexOf(\"you\", 30,60) -- \n" + lyrics.indexOf("you", 30,60));
        out.println(" --  lyrics.splitWithDelimiters(\"you\", 80) -- \n" + Arrays.toString(lyrics.splitWithDelimiters("you", 80)));
    }
}
