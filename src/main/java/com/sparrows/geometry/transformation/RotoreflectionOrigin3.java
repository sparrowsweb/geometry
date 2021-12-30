package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

public class RotoreflectionOrigin3 extends LinearTransformation3 {
    private Vector3 axis;
    private double angle;

    public double getAngle() {
        return angle;
    }

    public RotoreflectionOrigin3(Vector3 axis, double angle) throws GeometryException {
        super(matrix(axis.unit(), angle));
        this.axis = axis;
        this.angle = angle;
    }

    public static SimpleMatrix matrix(Vector3 axis, double angle) {
        return ReflectionOrigin3.matrix(axis).mult(RotationOrigin3.matrix(axis, angle));
    }
}
