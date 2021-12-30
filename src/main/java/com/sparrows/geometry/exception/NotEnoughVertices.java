package com.sparrows.geometry.exception;

public class NotEnoughVertices extends GeometryException {
    public NotEnoughVertices() {
        super("A polygon must have at least three vertices.");
    }
}
