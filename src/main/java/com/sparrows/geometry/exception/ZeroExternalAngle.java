package com.sparrows.geometry.exception;

public class ZeroExternalAngle extends GeometryException {
    public ZeroExternalAngle(int v) {
        super("Zero external angle at vertex " + v + ".");
    }
}
