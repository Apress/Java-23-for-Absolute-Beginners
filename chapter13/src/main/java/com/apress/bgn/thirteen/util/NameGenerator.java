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
package com.apress.bgn.thirteen.util;

import java.util.random.RandomGenerator;

/**
 * @author iuliana.cosmina on 27/08/2024
 * @version TODO
 */
public class NameGenerator {
    public static RandomGenerator RND = RandomGenerator.of("SecureRandom");
    private static final String letters = "abcdefghijk lmnopqrstuvwzyz";
    private final char[] name;

    public NameGenerator() {
        name = new char[15];
    }

    public NameGenerator(int capacity) {
        name = new char[capacity];
    }

    public String genName() {
        for (int i = 0; i < name.length; ++i) {
            name[i] = letters.charAt(RND.nextInt(letters.length()));
        }
        name[0] = (char) (name[0] - 32);
        return new String(name);
    }
}
