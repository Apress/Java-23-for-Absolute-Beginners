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
package com.apress.bgn.four.exceptions;

import com.apress.bgn.four.hierarchy.Performer;
import com.apress.bgn.four.classes.Gender;

/**
 * Created by iuliana.cosmina on 16/04/2024
 */
class DummyInitializer {

    public Performer getPerformer(String name) {
        Performer p = PerformerGenerator.get(name);
        try {
            System.out.println("Test if valid: " + p.getName());
        } catch (Exception e) {
            p = new Performer("Dummy", 0, 0.0f, Gender.UNSPECIFIED);
        }
        return p;
    }
}

public class DummyInitExceptionDemo {
    public static void main(String... args) {
        DummyInitializer initializer = new DummyInitializer();
        Performer p = initializer.getPerformer("Bryce");

        if("Dummy".equals(p.getName())) { // different behaviour based on performer name
            System.out.println("Nothing to do.");
        } else {
            System.out.println("TTL: " + p.getTimeToLive());
        }
    }
}
