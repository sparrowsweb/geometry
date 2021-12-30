package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.transformation.d3.LinearTransformation3;

public class Scale3 extends LinearTransformation3 {
    private final double factor;

    public Scale3(double factor) {
        super(matrix(factor));
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public static SimpleMatrix matrix(double factor) {
        return new SimpleMatrix(new double[][]{{factor,0,0},{0,factor,0},{0,0,factor}});
    }
}
