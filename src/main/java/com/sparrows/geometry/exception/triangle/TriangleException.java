package com.sparrows.geometry.exception.triangle;

import com.sparrows.geometry.exception.GeometryException;

public class TriangleException extends GeometryException {
    public TriangleException(String message) {
        super("Invalid triangle: " + message);
    }
}
