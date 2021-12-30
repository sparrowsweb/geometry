package com.sparrows.geometry.exception.triangle;

public class TriangleSideNotANumberException extends TriangleException {
    public TriangleSideNotANumberException(int side, double value) {
        super("Side " + side + " length is not a number (" + value + ").");
    }
}
