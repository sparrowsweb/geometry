package com.sparrows.geometry.exception;

public class NotEnoughSphericalFaces extends GeometryException {
    public NotEnoughSphericalFaces() {
        super("A spherical polyhedron must have at least two faces.");
    }
}
