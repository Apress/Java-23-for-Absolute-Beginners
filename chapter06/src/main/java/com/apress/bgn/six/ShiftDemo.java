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

import static com.apress.bgn.six.BitwiseDemo.print8Bits;
import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 15/05/2024
 */
public class ShiftDemo {
    void main(){
        shiftLeft();
        out.println("-------------------------");
        shiftRight();
        out.println("-------------------------");
        unsignedShiftRight();
        out.println("-------------------------");
    }

    public static void shiftLeft(){
        byte b1 = 12;
        print8Bits(b1);
        byte b2 = (byte) (b1 << 3);
        print8Bits(b2);
    }

    public static void shiftRight(){
        // positive number
        byte b1 = 96;
        print8Bits(b1);
        byte b2 = (byte) (b1 >> 3);
        print8Bits(b2);
        // negative number
        out.println( " -- ");
        int i1 = -96;
        print32Bits(i1);
        int i2 =  i1 >> 3;
        print32Bits(i2);
    }

    public static void unsignedShiftRight(){
        int i1 = -16;
        print32Bits(i1);
        int i2 = i1 >>> 1;
        print32Bits(i2);
    }

    public static void print32Bits(int arg) {
        out.println("decimal:" + arg);
        String str = arg > 0 ?
                String.format("%32s", Integer.toBinaryString(arg)).replace(' ', '0') :
                String.format("%32s", Integer.toBinaryString(arg)).replace(' ', '1') ;
        out.println("binary: " + str);
    }
}
