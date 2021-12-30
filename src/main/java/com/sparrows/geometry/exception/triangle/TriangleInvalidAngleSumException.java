package com.sparrows.geometry.exception.triangle;

public class TriangleInvalidAngleSumException extends TriangleException {
    public TriangleInvalidAngleSumException(double value) {
        super("Angles must sum to π (actual value " + value + ").");
    }
}
