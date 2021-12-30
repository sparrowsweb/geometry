package com.sparrows.geometry.exception;

public class InvalidSchwarzTriangle extends GeometryException {
    public InvalidSchwarzTriangle(String message) {
        super("Invalid Schwarz Triangle: " + message);
    }
}
