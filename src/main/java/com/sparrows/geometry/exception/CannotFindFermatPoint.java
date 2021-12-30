package com.sparrows.geometry.exception;

import com.sparrows.geometry.spherical.SphericalPoint;

public class CannotFindFermatPoint extends GeometryException {
    public CannotFindFermatPoint() {
        super("Cannot find Fermat point.");
    }
}
