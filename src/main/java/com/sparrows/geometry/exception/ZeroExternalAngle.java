package com.sparrows.geometry.exception;

public class ZeroExternalAngle extends IllegalArgumentException {
    public ZeroExternalAngle(int v) {
        super("Zero external angle at vertex " + v + ".");
    }
}
