package com.sparrows.geometry.exception;

public class InvalidSidesDensity extends GeometryException {
    public InvalidSidesDensity(int sides, int density) {
        super("Inconsistent sides and density (" + sides + "/" + density + ").");
    }
}
