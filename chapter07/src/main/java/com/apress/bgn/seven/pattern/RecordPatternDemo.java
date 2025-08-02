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
package com.apress.bgn.seven.pattern;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 18/06/2024
 */

record FullName (String firstName, String lastName){ }

record PersonRecord (FullName fullName, Integer age) {}

record WrapperBeing<T>(T t, String description) { }

public class RecordPatternDemo {

    void main(){
        Object obj = genRandomRecordInstance();

         switch (obj) {
            case null -> out.println("no record");
            //case FullName fn -> out.println(fn);
            case FullName(String fn, String ln) -> out.println("FullName record: " + fn +" " + ln);
            case PersonRecord(FullName(var fn, String _), var age) -> out.println("Person record " + fn +"  of age " + age);
            default -> out.println("something else");
        }
    }

    public static Object genRandomRecordInstance(){
        var t = System.currentTimeMillis();

        if (t % 3 == 0 ) {
            return new FullName("John", "Doe");
        } else if (t % 5 == 0 ) {
            return new PersonRecord(new FullName("John", "Doe"), 42);
        } else if (t % 11 == 0) {
            return new WrapperBeing<>(new PersonRecord(new FullName("John", "Doe"), 42), "is mise");
        }
        return null;
    }
}

