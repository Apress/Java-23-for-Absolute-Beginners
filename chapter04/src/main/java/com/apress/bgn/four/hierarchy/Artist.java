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
package com.apress.bgn.four.hierarchy;

import com.apress.bgn.four.classes.Gender;

import java.util.Date;

/**
 * Created by iuliana.cosmina on 21/04/2024
 */
public interface Artist {
    public static final int LIFESPAN = 100;

    String getSchool();

    void setSchool(String school);

    //boolean isCreative();
    default boolean isCreative() {
        explain();
        return true;
    }

    private void explain() { // TODO make this line bold
        log();
        System.out.println("A true artist has a creative nature.");
    }

    private static void log() {
        System.out.println("[LOG] checked creativity on: " + new Date());
    }


    public static String capitalize(final String name) {
        Character c = name.charAt(0);
        if (Character.isLowerCase(c)) {
            Character upperC = Character.toUpperCase(c);
            name.replace(c, upperC);
        }
        return name;
    }


    /**
     * Yes this is allowed and you can run this. ;)
     */
    public static void main() {
        Artist artist =  new Performer("Myles Kennedy", 54, 1.8F, Gender.MALE);
        artist.isCreative();
    }
}
