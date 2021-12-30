package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

public class RotationOrigin3 extends LinearTransformation3 {
    private Vector3 axis;
    private double angle;

    public double getAngle() {
        return angle;
    }

    public RotationOrigin3(Vector3 axis, double angle) throws GeometryException {
        super(matrix(axis.unit(), angle));
        this.axis = axis;
        this.angle = angle;
    }
/*
    public RotationOrigin3(SignedLine3 axis, double angle) {
        this(axis.getVector(), angle);
    }
*/
    public static SimpleMatrix matrix(Vector3 axis, double angle) {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        return new SimpleMatrix(
                new double[][]{
                        {axis.getX() * axis.getX() * (1 - cosAngle) + cosAngle,
                                axis.getY() * axis.getX() * (1 - cosAngle) - axis.getZ() * sinAngle,
                                axis.getZ() * axis.getX() * (1 - cosAngle) + axis.getY() * sinAngle},
                        {axis.getX() * axis.getY() * (1 - cosAngle) + axis.getZ()*sinAngle,
                                axis.getY() * axis.getY() * (1 - cosAngle) + cosAngle,
                                axis.getY() * axis.getZ() * (1 - cosAngle) - axis.getX()*sinAngle},
                        {axis.getZ() * axis.getX() * (1 - cosAngle) - axis.getY()*sinAngle,
                                axis.getZ() * axis.getY() * (1 - cosAngle) + axis.getX()*sinAngle,
                                axis.getZ() * axis.getZ() * (1 - cosAngle) + cosAngle}
                }
        );
    }
}
