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
import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 30/04/2024
 */
public class NewTheadSyntax21Demo {
    void main() {
        // Os Thread
        Thread.ofPlatform()
                .name("Thread-P")
                .start(() -> out.println(" >> Hi, I am " + Thread.currentThread().getName() + " and I am an Platform thread! "));

        // Virtual thread
        Thread.startVirtualThread(() -> out.println(" >> Hi, I am " + Thread.currentThread()+" and I am a Virtual thread!"));

        // using VirtualThreadBuilder 'under the bonnet'
        Thread.ofVirtual()
                .name("Thread-V1")
                .start(() -> out.println(" >> Hi, I am " + Thread.currentThread().getName()+" and I am also a Virtual thread! "));

        // or creating it unstarted
        Thread vt = Thread.ofVirtual()
                .name("Thread-V2")
                .unstarted(() -> out.println(" >> Hi, I am " + Thread.currentThread().getName()+" and I am also a Virtual thread! "));
        vt.start();

        try {
            Thread.sleep(2000); // making sure 'main' thread finishes execution last
        } catch (InterruptedException _) {}
    }
}
