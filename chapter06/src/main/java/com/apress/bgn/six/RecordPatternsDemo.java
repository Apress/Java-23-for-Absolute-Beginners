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
package com.apress.bgn.six;

/**
 * Created by iuliana.cosmina on 12/05/2024
 */

record FullName (String firstName, String lastName){ }

record PersonRecord (FullName fullName, Integer age) {}

record WrapperBeing<T>(T t, String description) { }

public class RecordPatternsDemo {
    void main() {
        Object john = new FullName("John", "Mayer");
        Object johnRecord = new PersonRecord((FullName) john, 47);

        // JEP 440. Pattern Matching for instanceof with Records (Java 19)
        if (john instanceof FullName full) {
            System.out.println("FullName: " + full);
        }

        // extracting the values of the pattern variable using a deconstruction pattern
        if (john instanceof FullName(String firstName, String lastName)) {
            System.out.println("[Deconstruction] FirstName: " + firstName);
            System.out.println("[Deconstruction] LastName: " + lastName);
        }

        // works with `var` and nested records too
        if (johnRecord instanceof PersonRecord(FullName(var firstName, String lastName), var age)) {
            System.out.println("[Nested] FirstName: " + firstName);
            System.out.println("[Nested] LastName: " + lastName);
            System.out.println("[Nested] Age: " + age);
        }

        // works with generics too
        WrapperBeing<PersonRecord> wrapper = new WrapperBeing<>((PersonRecord) johnRecord, "is mise Iain");
        if (wrapper instanceof WrapperBeing<PersonRecord>(var personRecord, var description)) {
            System.out.println("[Generics] PersonRecord: " + personRecord);
            System.out.println("[Generics] Description: " + description);
        }

        // Unnamed Pattern - stolen from Go :D
        if (johnRecord instanceof PersonRecord (_, var age)) {
            System.out.println("[Unnamed Pattern] Age : " + age);
        }
        if (johnRecord instanceof PersonRecord (FullName (var _, String lastName), var _)) {
            System.out.println("[Unnamed Variable] LastName: " + lastName);
        }
    }
}


