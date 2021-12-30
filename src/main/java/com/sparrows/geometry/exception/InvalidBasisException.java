package com.sparrows.geometry.exception;

public class InvalidBasisException extends GeometryException {
    public InvalidBasisException() {
        super("Basis is invalid because it does not span all space.");
    }
}
