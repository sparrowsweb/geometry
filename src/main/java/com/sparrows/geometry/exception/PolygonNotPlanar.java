package com.sparrows.geometry.exception;

public class PolygonNotPlanar extends GeometryException {
    public PolygonNotPlanar() {
        super("Polygon is not planar.");
    }
}
