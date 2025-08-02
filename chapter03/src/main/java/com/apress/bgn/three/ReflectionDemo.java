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
package com.apress.bgn.three;

import com.apress.bgn.zero.Base;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Field;

/**
 * @author iuliana.cosmina on 30/08/2024
 */
public class ReflectionDemo {

    private static final Logger LOGGER = LogManager.getLogger();

    void main(){
        //testing access to Base class from module chapter.zero
        Base base = new Base();
        LOGGER.info("Base object was created? >  {} ", (base != null));

        //testing reflection
        try {
            Field field = base.getClass().getDeclaredField("secret"); // <1>
            field.setAccessible(true); // make the private field accessible
            field.set(base, 1); // set the value of the private field
            base.printSecret(); // call public method to display value of private field
        } catch (NoSuchFieldException nsf) {
            LOGGER.error("Field 'secret' cannot be accessed!" );
        } catch (IllegalAccessException e) {
            LOGGER.error("Field 'secret' cannot be set!" );
        }
    }
}
