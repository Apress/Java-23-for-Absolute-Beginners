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

import com.apress.bgn.four.classes.Gender;
import com.apress.bgn.four.hierarchy.Artist;
import com.apress.bgn.four.hierarchy.Musician;
import com.apress.bgn.four.hierarchy.Performer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina  on 12/02/2024
 */
public class OperatorDemo {
    public static void main() {
        List<Artist> artists = new ArrayList<>();

        Musician john = new Performer("John", 47, 1.91f, Gender.MALE);
        List<String> songs = List.of("Gravity");
        john.setSongs(songs);
        artists.add(john);

        Graphician diana = new Graphician("Diana", 23, 1.62f, Gender.FEMALE, "macOs");
        artists.add(diana);

        // prev to Java 14
      /*  for (Artist artist : artists) {
            if (artist instanceof Musician) {
                Musician musician = (Musician) artist;
                out.println("Songs: " + musician.getSongs());
            } else {
                out.println("Other Type: " +  artist.getClass());
            }
        }*/

        // starting with Java 14
        for (Artist artist : artists) {
            if (artist instanceof Musician musician) {
                out.println("Songs: " + musician.getSongs());
            } else {
                out.println("Other Type: " + artist.getClass());
            }
        }

        // bad conversion
        //Musician fake = (Musician) diana;
    }

}
