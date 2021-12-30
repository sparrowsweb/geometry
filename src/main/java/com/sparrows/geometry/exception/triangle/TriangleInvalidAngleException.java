package com.sparrows.geometry.exception.triangle;

public class TriangleInvalidAngleException extends TriangleException {
    public TriangleInvalidAngleException(int angle, double value) {
        super("Angle " + angle + " (" + value + ") must be greater than zero and less than π.");
    }
}
