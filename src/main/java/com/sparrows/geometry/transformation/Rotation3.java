package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.geometry3.Line3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

public class Rotation3 extends AffineTransformation3 {
    private final Line3 axis;
    private final double angle;

    public Rotation3(Line3 axis, double angle) {
        super(matrix(axis, angle));
        this.axis = axis;
        this.angle = angle;
    }

    public Line3 getAxis() {
        return axis;
    }

    public double getAngle() {
        return angle;
    }

    public static SimpleMatrix matrix(Line3 axis, double angle) {
        var shift1 = new Translation3(new Vector3(axis.getPoint()).negate());
        LinearTransformation3 rotateOrigin = null;
        try {
            rotateOrigin = new RotationOrigin3(axis.getVector(),angle);
        } catch (GeometryException e) {
            // can't happen
        }
        var shift2 = new Translation3(new Vector3(axis.getPoint()));
        return new AffineTransformation3(shift1).compose(new AffineTransformation3(rotateOrigin)).compose(new AffineTransformation3(shift2)).getMatrix();
    }
}
