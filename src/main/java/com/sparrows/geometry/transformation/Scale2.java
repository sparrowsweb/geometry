package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class Scale2 extends LinearTransformation2 {
    private final double factor;

    public Scale2(double factor) {
        super(matrix(factor));
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public static SimpleMatrix matrix(double factor) {
        return new SimpleMatrix(new double[][]{{factor,0},{0,factor}});
    }
}
