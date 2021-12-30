package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Plane3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

// reflection in a plane through the origin
public class ReflectionOrigin3 extends LinearTransformation3 {
    private Vector3 mirrorNormal;

    public ReflectionOrigin3(Vector3 mirrorNormal) {
        super(matrix(mirrorNormal));
        this.mirrorNormal = mirrorNormal;
    }

    public ReflectionOrigin3(Plane3 a) {
        this(a.getNormal());
    }

    public static SimpleMatrix matrix(Vector3 mirrorNormal) {
        return new SimpleMatrix(
                new double[][]{
                        {1-2* mirrorNormal.getX()* mirrorNormal.getX(), -2* mirrorNormal.getX()* mirrorNormal.getY(), -2* mirrorNormal.getX()* mirrorNormal.getZ()},
                        {-2* mirrorNormal.getY()* mirrorNormal.getX(), 1-2* mirrorNormal.getY()* mirrorNormal.getY(), -2* mirrorNormal.getY()* mirrorNormal.getZ()},
                        {-2* mirrorNormal.getZ()* mirrorNormal.getX(), -2* mirrorNormal.getZ()* mirrorNormal.getY(), 1-2* mirrorNormal.getZ()* mirrorNormal.getZ()}
                }
        );
    }

    public Vector3 getMirrorNormal() {
        return this.mirrorNormal;
    }
}
