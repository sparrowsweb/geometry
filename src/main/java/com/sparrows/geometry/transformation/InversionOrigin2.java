package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class InversionOrigin2 extends LinearTransformation2 {

    public InversionOrigin2() {
        super(matrix());
    }

    public static SimpleMatrix matrix() {
        return new SimpleMatrix(new double[][]{{-1,0},{0,-1}});
    }
}
