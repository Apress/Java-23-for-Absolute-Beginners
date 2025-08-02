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
package com.apress.bgn.four.hidden;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by iuliana.cosmina on 14/04/2024
 */

public class HiddenClassDemo {

    public void doTheThing() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        // Initiate lookup class
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // Create a byte code of a class
        Class<?> clazz = MyHiddenClass.class;
        String className = clazz.getName();
        String classAsPath = className.replace('.', '/') + ".class";
        InputStream stream = clazz.getClassLoader().getResourceAsStream(classAsPath);
        byte[] bytes = IOUtils.toByteArray(stream);

        //Creating a hidden class from bytes
        Class<?> hiddenClass = lookup.defineHiddenClass(bytes, true, MethodHandles.Lookup.ClassOption.NESTMATE).lookupClass();
        // ClassOption.NESTMATE specifies that the created hidden class will be added as a nestmate to the lookup class so that it has access to the private members of all classes and interfaces in the same nest.
        System.out.println("Is MyHiddenClass hidden: " + hiddenClass.isHidden());
        System.out.println("MyHiddenClass canonical name: " + hiddenClass.getCanonicalName());
        System.out.println("This App ClassLoader: " + getClass().getClassLoader());
        System.out.println("ClassLoader: " + hiddenClass.getClassLoader());

        Object hiddenClassObject = hiddenClass.getConstructor().newInstance();
        try {
            Class.forName(hiddenClass.getName());
        }catch (Exception e) {
            System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
        }

        try {
            lookup.findClass(hiddenClass.getName());
        }catch (Exception e) {
            System.out.println(e.getClass().getCanonicalName() + ": " + e.getMessage());
        }

        Method method = hiddenClassObject.getClass().getDeclaredMethod("sayHello", String.class);
        String res =  (String) method.invoke(hiddenClassObject, "Developer");
        System.out.println(res);
    }

    public static void main(String... args) throws Exception {
       new HiddenClassDemo().doTheThing();
    }
}
