package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.transformation.d3.LinearTransformation3;

public class Stretch3 extends LinearTransformation3 {
    private final double xFactor;
    private final double yFactor;
    private final double zFactor;

    public Stretch3(double xFactor, double yFactor, double zFactor) {
        super(matrix(xFactor, yFactor, zFactor));
        this.xFactor = xFactor;
        this.yFactor = yFactor;
        this.zFactor = zFactor;
    }

    public double getXFactor() {
        return xFactor;
    }

    public double getYFactor() {
        return yFactor;
    }

    public double getZFactor() {
        return zFactor;
    }

    public static SimpleMatrix matrix(double xFactor, double yFactor, double zFactor) {
        return new SimpleMatrix(new double[][]{{xFactor,0,0},{0,yFactor,0},{0,0,zFactor}});
    }
}
