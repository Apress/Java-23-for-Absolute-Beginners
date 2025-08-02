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

import com.apress.bgn.four.classes.Gender;
import com.apress.bgn.four.hierarchy.MiliVanili;
import com.apress.bgn.four.hierarchy.Performer;
import com.apress.bgn.six.Graphician;

import java.util.List;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 28/05/2024
 */
public class PatternDemo {

    void main(){
        Object obj = genRandomInstance();

        var res = switch (obj) {
            case null -> "no object";
            case Performer p when p.getAge() > 80 -> {
                out.println("This is a classic. ");
                yield  "Performed in: " + p.getFilms();
            }
            case MiliVanili m -> "Creativity " + (m.isCreative() ? "found" : "not found");
            case Graphician g -> "Preffers " + g.getFavoriteOs();
            case Painter p -> "Style: " + p.getStyle();
            default -> "something else";
        };
        out.println(res);
    }

    public static Object genRandomInstance(){
        var t = System.currentTimeMillis();

        if (t % 3 == 0 ) {
            return new Painter("Pablo Picasso", "Cubism", "School of Fine Arts Barcelona");
        } else if (t % 5 == 0 ) {
            return new Performer("Sean", 94, 1.88f, Gender.MALE, List.of("James Bond"));
        } else if (t % 11 == 0) {
            return new MiliVanili();
        } else if (t % 17 == 0) {
           return new Graphician("Diana", 23, 1.62f, Gender.FEMALE, "macOs");
        } else  if (t % 23== 0) {
            return "random text";
        } else  if (t % 31== 0) {
            return Integer.MAX_VALUE;
        }
        return null;
    }
}


