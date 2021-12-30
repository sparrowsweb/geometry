package com.sparrows.geometry.exception.triangle;

public class TriangleAngleNotANumberException extends TriangleException {
    public TriangleAngleNotANumberException(int angle, double value) {
        super("Angle " + angle + " value is not a number (" + value + ").");
    }
}
