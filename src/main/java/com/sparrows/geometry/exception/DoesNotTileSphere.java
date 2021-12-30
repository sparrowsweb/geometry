package com.sparrows.geometry.exception;

public class DoesNotTileSphere extends GeometryException {
    public DoesNotTileSphere() {
        super("This triangle does not tile the sphere.");
    }
}
