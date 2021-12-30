package com.sparrows.geometry.exception;

public class IdenticalVertices extends GeometryException {
    public IdenticalVertices() {
        super("A polygon may not have coincident vertices.");
    }
}
