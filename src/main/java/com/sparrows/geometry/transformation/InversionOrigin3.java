package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.transformation.d3.LinearTransformation3;

public class InversionOrigin3 extends LinearTransformation3 {

    public InversionOrigin3() {
        super(matrix());
    }

    public static SimpleMatrix matrix() {
        return new SimpleMatrix(new double[][]{{-1,0,0},{0,-1,0},{0,0,-1}});
    }
}
