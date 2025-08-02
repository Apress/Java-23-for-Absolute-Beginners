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
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.out;

/**
 * Created by iuliana.cosmina on 29/04/2024
 */
public class SetDemo {

    void main() {
        Set<Ball> ballSet = new HashSet<>();
        ballSet.add(new Ball(2, "RED", "rubber"));
        ballSet.add(new Ball(4, "BLUE", "cotton"));

        out.println("Set size: " +  ballSet.size());
        Ball duplicate = new Ball(2, "RED", "rubber");
        boolean wasAdded = ballSet.add(duplicate);
        if(!wasAdded) {
            out.println("Duplicate ball not added to the set. ");
            out.println("Set size: " +  ballSet.size());
        }
    }
}

record Ball(int diameter, String colour, String material) {}

/*
class Ball {
    private int diameter;
    private String color;
    private String material;

    public Ball(int diameter, String color, String material) {
        this.diameter = diameter;
        this.color = color;
        this.material = material;
    }

    // after Java 1.7
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return diameter == ball.diameter &&
                Objects.equals(color, ball.color) &&
                Objects.equals(material, ball.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diameter, color, material);
    }

    // Pre Java 1.7
    */
/*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return diameter == ball.diameter &&
                color.equals(ball.color) &&
                material.equals(ball.material);
    }

    @Override
    public int hashCode() {
        int result = 17 * diameter;
        result = 31 * result + (color == null ? 0 : color.hashCode());
        result = 31 * result + (material == null ? 0 : material.hashCode());
        return result;
    }*//*

    // other code omitted
}*/
