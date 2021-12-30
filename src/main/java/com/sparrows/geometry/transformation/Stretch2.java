package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class Stretch2 extends LinearTransformation2 {
    private final double xFactor;
    private final double yFactor;

    public Stretch2(double xFactor, double yFactor) {
        super(matrix(xFactor, yFactor));
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    public double getXFactor() {
        return xFactor;
    }

    public double getYFactor() {
        return yFactor;
    }

    public static SimpleMatrix matrix(double xFactor, double yFactor) {
        return new SimpleMatrix(new double[][]{{xFactor,0},{0,yFactor}});
    }
}
