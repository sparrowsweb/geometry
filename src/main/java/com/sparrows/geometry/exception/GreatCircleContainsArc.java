package com.sparrows.geometry.exception;

public class GreatCircleContainsArc extends GeometryException {
    public GreatCircleContainsArc() {
        super("Arc lies within great circle.");
    }
}
