package com.sparrows.geometry.exception;

public class InvalidVertexAngles extends GeometryException {
    public InvalidVertexAngles() {
        super("Vertex angles must sum to more than π and less than 3π, and no angle may exceed the sum of the other two by π or more.");
    }
}
