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
package com.apress.bgn.four.classes;

/**
 * Created by iuliana.cosmina on 26/03/2024
 */
public class BasicHumanDemo {

    public static void main(String... args) {
        //Human alex = new Human();
        //Human bryce = new Human("Bryce", 38, 1.72f);
        Musician bryce = new Musician("Bryce", 38, 1.72f, "High School Rock", "Metal");

        System.out.println("name: " + bryce.getName());
        System.out.println("age: " + bryce.getAge());
        System.out.println("height: " + bryce.getHeight());

        int timeToLive = bryce.getTimeToLive();//
        System.out.println("Time to live: " + timeToLive);

        // --- random musician ---
        Musician randomMusician = new Musician();
        System.out.println("name: " + randomMusician.getName());
        System.out.println("age: " + randomMusician.getAge());
        System.out.println("height: " + randomMusician.getHeight());
        System.out.println("height: " + randomMusician.getMusicSchool());
    }
}
