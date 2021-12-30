package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.MirrorPointNotOnAxis;
import com.sparrows.geometry.geometry3.Line3;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

public class Rotoreflection3 extends AffineTransformation3 {
    private final Line3 axis;
    private final double angle;
    public final Point3 mirrorPoint;

    public Rotoreflection3(Line3 axis, double angle, Point3 mirrorPoint) throws GeometryException {
        super(matrix(axis, angle,mirrorPoint));
        if (!axis.contains(mirrorPoint)) {
            throw new MirrorPointNotOnAxis();
        }
        this.axis = axis;
        this.angle = angle;
        this.mirrorPoint = mirrorPoint;
    }

    public Line3 getAxis() {
        return axis;
    }

    public double getAngle() {
        return angle;
    }

    public Point3 getMirrorPoint() {
        return mirrorPoint;
    }

    public static SimpleMatrix matrix(Line3 axis, double angle, Point3 mirrorPoint) throws GeometryException {
        var shift1 = new Translation3(new Vector3(mirrorPoint).negate());
        LinearTransformation3 rotoreflectionOrigin = new RotoreflectionOrigin3(axis.getVector(),angle);
        var shift2 = new Translation3(new Vector3(mirrorPoint));
        return new AffineTransformation3(shift1).compose(new AffineTransformation3(rotoreflectionOrigin)).compose(new AffineTransformation3(shift2)).getMatrix();
    }
}
