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

import java.util.Date;

/**
 * Created by iuliana.cosmina on 21/04/2024
 */
public class PrimitivesDemo {

    void main() {
        var i =5;
        var j =7;
        var d = new Date();
        var result = add(i,j);
        System.out.println(result);
        d =null;

        // remove comment to trigger compile error for section ' The `boolean` Type'
       /* boolean f = false;
        int fi = (int) f;*/

        // remove comment to see precision loss when converting byte to short in section 'Java Integer Primitive Types'
        /*byte bv = 23;
        short sbv = bv;
        System.out.println("byte to short: " +   sbv);*/

        // remove comment to see precision loss when converting long to float in section 'Java Real Primitive Types'
        /*float maxLongF = Long.MAX_VALUE;
        System.out.println("max long= " + Long.MAX_VALUE);
        System.out.println("float max long= " + maxLongF);*/
    }

    static int add(int a, int b) {
        var mess = "performing add ... ";
        return a + b;
    }
}
