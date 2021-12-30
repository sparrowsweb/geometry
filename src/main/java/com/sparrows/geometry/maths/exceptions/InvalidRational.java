package com.sparrows.geometry.maths.exceptions;

public class InvalidRational extends Exception {
    public InvalidRational(String s) {
        super("Invalid Rational: " + s + ".");
    }
}
