package com.sparrows.geometry.exception.triangle;

public class TriangleInconsistentAngleSideSideException extends TriangleException {
    public TriangleInconsistentAngleSideSideException() {
        super("Cannot construct triangle from these values.");
    }
}
