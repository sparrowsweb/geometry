package com.sparrows.geometry.exception;

public class IdenticalVertices extends IllegalArgumentException {
    public IdenticalVertices() {
        super("A polygon may not have coincident vertices.");
    }
}
