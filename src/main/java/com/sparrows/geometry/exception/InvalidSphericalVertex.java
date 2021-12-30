package com.sparrows.geometry.exception;

import com.sparrows.geometry.spherical.SphericalPoint;

public class InvalidSphericalVertex extends GeometryException {
    public InvalidSphericalVertex(SphericalPoint vertex, int faces) {
        super("Vertex " + vertex.toString() + " borders only " + faces + " faces.");
    }
}
