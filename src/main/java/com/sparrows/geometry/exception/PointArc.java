package com.sparrows.geometry.exception;

public class PointArc extends GeometryException {
    public PointArc() {
        super("Arc has xero length.");
    }
}
