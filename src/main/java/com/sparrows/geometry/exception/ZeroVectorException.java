package com.sparrows.geometry.exception;

public class ZeroVectorException extends GeometryException {
    public ZeroVectorException() {
        super("Vector is zero.");
    }
}
