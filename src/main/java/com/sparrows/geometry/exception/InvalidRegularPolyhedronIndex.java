package com.sparrows.geometry.exception;

public class InvalidRegularPolyhedronIndex extends GeometryException {
    public InvalidRegularPolyhedronIndex() {
        super("Regular polyhedron index must be between 1 and 9.");
    }
}
