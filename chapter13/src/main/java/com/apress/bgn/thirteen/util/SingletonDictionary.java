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
package com.apress.bgn.thirteen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Cleaner;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author iuliana.cosmina on 28/08/2024
 */
public class SingletonDictionary {
    public static final Cleaner cleaner = Cleaner.create();
    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonDictionary.class);

    private static final SingletonDictionary instance = new SingletonDictionary();
    private Map<String, String> dictionary = new HashMap<>();

    private SingletonDictionary() {
        // init dictionary
        LOGGER.info("Starting to create dictionary: {}", System.currentTimeMillis());
        final NameGenerator keyGen = new NameGenerator(20);
        final NameGenerator valGen = new NameGenerator(200);
        IntStream.range(0, 100_000).forEach(_ -> dictionary.put(keyGen.genName(), valGen.genName()));
        LOGGER.info("Done creating dictionary: {}", System.currentTimeMillis());
    }

    public synchronized static SingletonDictionary getInstance(){
        return instance;
    }
}
