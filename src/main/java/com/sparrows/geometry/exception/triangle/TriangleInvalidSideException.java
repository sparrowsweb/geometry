package com.sparrows.geometry.exception.triangle;

public class TriangleInvalidSideException extends TriangleException {
    public TriangleInvalidSideException(int side, double value) {
        super("Side " + side + " length (" + value + ") must be greater than zero.");
    }
}
