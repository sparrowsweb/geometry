package com.sparrows.geometry.exception;

public class NotEnoughSphericalVertices extends GeometryException {
    public NotEnoughSphericalVertices() {
        super("A spherical polygon must have at least two vertices.");
    }
}
