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
 * Created by iuliana.cosmina on 14/05/2024
 */
public class PrimitivePatternsDemo {
    void main() {
        var generated = genVal();

        if (generated instanceof byte b){
            System.out.println("byte val = " + b);
        }
        if (generated instanceof short s){
            System.out.println("short val = " + s);
        }
        if (generated instanceof char c){
            System.out.println("char val = " + c);
        }
        if (generated instanceof int i) {
            System.out.println("int val = " + i);
        }
        if (generated instanceof long l) {
            System.out.println("long val = " + l);
        }
        if (generated instanceof float f) {
            System.out.println("float val = " + f);
        }
    }

    static long genVal() {
        var t = System.currentTimeMillis();
        if (t % 3 == 0) {
            byte bv = Byte.MAX_VALUE;
            return bv;
        } else if (t % 5 == 0) {
            short sbv = Short.MAX_VALUE;
            return sbv;
        } else if (t % 7 == 0) {
            long lv = Long.MAX_VALUE;
            return lv;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}

