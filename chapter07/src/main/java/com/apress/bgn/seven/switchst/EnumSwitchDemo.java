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
package com.apress.bgn.seven.switchst;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 18/05/2024
 */
public class EnumSwitchDemo {
    enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST,
        SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

    void main(String... args) {
        //Read a
        String a = args[0];
        try {
            Month month = Month.valueOf(a.toUpperCase());
            var season = "";
            switch (month) {
                case JANUARY:
                case FEBRUARY:
                case DECEMBER:
                    season = "Winter";
                    break;
                case MARCH:
                case APRIL:
                case MAY:
                    season = "Spring";
                    break;
                case JUNE:
                case JULY:
                case AUGUST:
                    season = "Summer";
                    break;
                case SEPTEMBER:
                case OCTOBER:
                case NOVEMBER:
                    season = "Autumn";
                    break;
            }
            out.println(season);
        } catch(IllegalArgumentException iae) {
            out.println("Unrecognized enum value: " + a);
        }
    }
}
