package com.sparrows.geometry.exception;

public class TransformationNotInvertible extends GeometryException {
    public TransformationNotInvertible() {
        super("Transformation has no inverse.");
    }
}
