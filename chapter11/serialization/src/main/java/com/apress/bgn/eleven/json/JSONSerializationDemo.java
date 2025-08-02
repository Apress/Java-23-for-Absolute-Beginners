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
package com.apress.bgn.eleven.json;

import com.apress.bgn.eleven.xml.Singer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class JSONSerializationDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONSerializationDemo.class);

    void main(){
        var johnBd = LocalDate.of(1977, Month.OCTOBER, 16);
        var john = new Singer("John Mayer", 5.0, johnBd);

        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        var path = Path.of("chapter11/serialization/src/test/resources/output/john.json");
        try {
            var xml = jsonMapper.writeValueAsString(john);
            Files.writeString(path, xml,
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.info("Serialization to JSON failed! ", e);
        }

        try {
            var copyOfJohn = jsonMapper.readValue(path.toFile(), Singer.class);
            LOGGER.info("Are objects equal? {}", copyOfJohn.equals(john));
            LOGGER.info("--> {}", copyOfJohn);
        } catch (IOException e) {
            LOGGER.info("Deserialization of JSON failed! ", e);
        }
    }
}
