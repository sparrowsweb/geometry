package com.sparrows.geometry.exception;

public class NotEnoughFaces extends GeometryException {
    public NotEnoughFaces() {
        super("A polyhedron must have at least four faces.");
    }
}
