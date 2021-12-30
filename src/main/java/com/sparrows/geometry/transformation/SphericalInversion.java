package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class SphericalInversion extends SphericalTransformation {
    public SphericalInversion() {
        this.matrix = SimpleMatrix.identity(3).negative();
    }
}
