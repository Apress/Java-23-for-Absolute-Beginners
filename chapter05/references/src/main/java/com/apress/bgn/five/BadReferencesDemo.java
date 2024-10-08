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

import com.apress.bgn.four.classes.Gender;
import com.apress.bgn.four.hierarchy.Musician;
import com.apress.bgn.four.hierarchy.Performer;

import java.util.List;

/**
 * Created by iuliana.cosmina on 21/04/2024
 */
public class BadReferencesDemo {

    void main() {
        // Listing 5-16 , remove comment to trigger compile error
        /*Performer performer = new Performer("John", 47, 1.91f, Gender.MALE);
        Human human = performer;
        Actor actor = performer;
        Musician musician = performer;
        //these will not compile!!!
        performer = musician;
        //or
        performer = human;
        //or
        performer = actor;*/


        // Listing 5-17 , remove comment and run to trigger runtime error
        Musician fiddler = new Fiddler(true);
        Performer performer = (Performer) fiddler;
        System.out.println("Learned the skill at: " + performer.getSchool());
        System.out.println("Appeared in movies: " + performer.getFilms());
    }

}

class Fiddler implements Musician {
    private boolean ownsFiddle = false;

    public Fiddler(boolean ownsFiddle) {
        this.ownsFiddle = ownsFiddle;
    }

    @Override
    public String getSchool() {
        return "Irish Conservatory";
    }

    @Override
    public void setSchool(String school) {

    }

    @Override
    public List<String> getSongs() {
        return null;
    }

    @Override
    public void setSongs(List<String> songs) {

    }

    @Override
    public String getGenre() {
        return null;
    }

    @Override
    public void setGenre(String genre) {

    }
}