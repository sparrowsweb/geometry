package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class SphericalIdentity extends SphericalTransformation {

    public SphericalIdentity() {
        super();
        this.matrix = SimpleMatrix.identity(3);
    }
}
