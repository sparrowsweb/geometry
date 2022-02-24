package com.sparrows.geometry.exception;

public class PolygonNotPlanar extends IllegalArgumentException {
    public PolygonNotPlanar() {
        super("Polygon is not planar.");
    }
}
