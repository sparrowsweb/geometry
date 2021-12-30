package com.sparrows.geometry.exception;

public class InvalidSphericalPoint extends GeometryException {
    public InvalidSphericalPoint() {
        super("Point does not lie on the unit sphere.");
    }
}
