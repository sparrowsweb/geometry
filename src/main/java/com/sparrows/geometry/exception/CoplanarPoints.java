package com.sparrows.geometry.exception;

public class CoplanarPoints extends GeometryException {
    public CoplanarPoints() {
        super("Points are coplanar.");
    }
}
