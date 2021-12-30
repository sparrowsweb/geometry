package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry2.Line2;
import org.ejml.simple.SimpleMatrix;

// reflection in a line through the origin
public class ReflectionOrigin2 extends LinearTransformation2 {
    private double mirrorAngle;

    public double getMirrorAngle() {
        return mirrorAngle;
    }

    public ReflectionOrigin2(double mirrorAngle) {
        super(new SimpleMatrix(
                new double[][]{
                        {Math.cos(2* mirrorAngle), Math.sin(2* mirrorAngle)},
                        {Math.sin(2* mirrorAngle), -Math.cos(2* mirrorAngle)}
                }
        ));
        this.mirrorAngle = mirrorAngle;
    }

    public ReflectionOrigin2(Line2 l) {
        this(Math.atan2(l.getVector().getY(),l.getVector().getX()));
    }
}
