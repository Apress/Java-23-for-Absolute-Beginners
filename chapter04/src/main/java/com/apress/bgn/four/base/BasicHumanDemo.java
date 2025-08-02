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
package com.apress.bgn.four.base;

import com.apress.bgn.four.classes.Gender;

/**
 * Created by iuliana.cosmina on 20/03/2024
 */
public class BasicHumanDemo {
    public static void main(String... args) {
        //Human human = new Human();
        Human human = new Human("", 0);
        System.out.println("name: " + human.getName());
        System.out.println("age: " + human.getAge());
        System.out.println("height: " + human.getHeight());

        //Human alex = new Human();
        Human alex = new Human("", 0);
        alex.setName("Alex");
        alex.setAge(40);
        alex.setHeight(1.91f);

        System.out.println("name: " + alex.getName());
        System.out.println("age: " + alex.getAge());
        System.out.println("height: " + alex.getHeight());

        int timeToLive = alex.getTimeToLive();//
        System.out.println("Time to live: " + timeToLive);

        System.out.println("Alex’s lifespan = " + alex.LIFESPAN);  // prints 100
        System.out.println("human’s lifespan = " + human.LIFESPAN);  // prints 100
        System.out.println("Human lifespan = " + Human.LIFESPAN);  // prints 100

        for (Gender value : Gender.values()){
            System.out.println(value.name() + ": " + value.getClass());
            System.out.println("\tcomment : " + value.comment());
        }
    }
}
