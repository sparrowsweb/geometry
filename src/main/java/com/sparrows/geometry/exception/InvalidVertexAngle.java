package com.sparrows.geometry.exception;

public class InvalidVertexAngle extends GeometryException {
    public InvalidVertexAngle() {
        super("Vertex angle must be greater than 0 and less than π.");
    }
}
