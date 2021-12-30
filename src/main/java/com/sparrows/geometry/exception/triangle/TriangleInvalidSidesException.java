package com.sparrows.geometry.exception.triangle;

public class TriangleInvalidSidesException extends TriangleException {
    public TriangleInvalidSidesException(int side, double value) {
        super("Side " + side + " length (" + value + ") must be less than the sum of the other two sides.");
    }
}
