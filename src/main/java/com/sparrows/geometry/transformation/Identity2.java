package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class Identity2 extends LinearTransformation2 {

    public Identity2() {
        super(matrix());
    }

    public static SimpleMatrix matrix() {
        return SimpleMatrix.identity(2);
    }
}
