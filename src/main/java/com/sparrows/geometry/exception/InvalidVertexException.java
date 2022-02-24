package com.sparrows.geometry.exception;

import com.sparrows.geometry.geometry3.Point3;

public class InvalidVertexException extends IllegalArgumentException {
    public InvalidVertexException(Point3 vertex, int faces) {
        super("Vertex " + vertex.toString() + " borders only " + faces + " faces.");
    }
}
