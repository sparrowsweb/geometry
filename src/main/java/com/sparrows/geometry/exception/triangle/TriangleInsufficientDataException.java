package com.sparrows.geometry.exception.triangle;

public class TriangleInsufficientDataException extends TriangleException {
    public TriangleInsufficientDataException() {
        super("Insufficient data to compute triangle.");
    }
}
