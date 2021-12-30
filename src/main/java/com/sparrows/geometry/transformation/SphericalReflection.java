package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.spherical.GreatCircle;

public class SphericalReflection extends SphericalTransformation {
    private GreatCircle mirror;

    public SphericalReflection(GreatCircle mirror) {
        super();
        this.mirror = mirror;
        this.matrix = matrix(mirror);
    }

    public static SimpleMatrix matrix(GreatCircle mirror) {
        return new SimpleMatrix(
                new double[][]{
                        {1-2* mirror.getCentre().getX()* mirror.getCentre().getX(), -2* mirror.getCentre().getX()* mirror.getCentre().getY(), -2* mirror.getCentre().getX()* mirror.getCentre().getZ()},
                        {-2* mirror.getCentre().getY()* mirror.getCentre().getX(), 1-2* mirror.getCentre().getY()* mirror.getCentre().getY(), -2* mirror.getCentre().getY()* mirror.getCentre().getZ()},
                        {-2* mirror.getCentre().getZ()* mirror.getCentre().getX(), -2* mirror.getCentre().getZ()* mirror.getCentre().getY(), 1-2* mirror.getCentre().getZ()* mirror.getCentre().getZ()}
                }
        );
    }
}
