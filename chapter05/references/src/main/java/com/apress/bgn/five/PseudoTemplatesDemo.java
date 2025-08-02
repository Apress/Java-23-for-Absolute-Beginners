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

import com.apress.bgn.four.classes.Musician;

import java.text.MessageFormat;

/**
 * Created by iuliana.cosmina on 28/04/2024
 */
public class PseudoTemplatesDemo {

    void main() {
        var bryce = new Musician("Bryce", 38, 1.72f, "High School Rock", "Metal");

        // hard to read
        // concatenation using +
        var introduction = "My name is " + bryce.getName() +"  and I am "
                + bryce.getAge() +" years old.";
        System.out.println("[using '+'] -> " + introduction);

        // still hard to read
        // concatenation using String#concat
        introduction = "My name is ".concat(bryce.getName()).concat("  and I am ")
                .concat(String.valueOf(bryce.getAge())).concat(" years old.");
        System.out.println("[using 'concat(..)'] -> " + introduction);

        // verbose, hard to read as well
        // concatenation using StringBuilder
        // similar approach to using StringBuffer
        introduction = new StringBuilder("My name is ").append(bryce.getName()).append("  and I am ")
                .append(bryce.getAge() ).append(" years old.").toString();
        System.out.println("[using 'StringBuilder#append(..)' ] -> " + introduction);

        // no of arguments mismatch
        // concatenation using String.format
        introduction = String.format("My name is %s and I am %d years old.", bryce.getName() , bryce.getAge());
        System.out.println("[using `String.format(..)`] -> " + introduction);

        // no of arguments mismatch
        // concatenation using MessageFormat.format
        introduction = MessageFormat.format("My name is {0} and I am {1} years old.", bryce.getName() , bryce.getAge());
        System.out.println("[using 'MessageFormat.format(..)'] -> " + introduction);

        // Java 15
        // concatenation using String#formatted
        introduction = introduction.formatted(bryce.getName() , bryce.getAge());
        System.out.println("[using 'String#formatted(..)']:  -> " + introduction);

    }
}
