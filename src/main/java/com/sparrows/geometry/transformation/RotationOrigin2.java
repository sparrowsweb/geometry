package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class RotationOrigin2 extends LinearTransformation2 {
    private double angle;

    public double getAngle() {
        return angle;
    }

    public RotationOrigin2(double angle) {
        super(new SimpleMatrix(
                new double[][]{
                        {Math.cos(angle), -Math.sin(angle)},
                        {Math.sin(angle), Math.cos(angle)}
                }
        ));
        this.angle = angle;
    }
}
