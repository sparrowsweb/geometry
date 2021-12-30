package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.exception.InvalidSphericalTransformation;
import com.sparrows.geometry.spherical.SphericalPoint;

public class SphericalRotation extends SphericalTransformation {
    private final SphericalPoint centre;
    private final double angle;

    public double getAngle() {
        return angle;
    }

    public SphericalRotation(SphericalPoint centre, double angle) throws InvalidSphericalTransformation {
        super();
        this.matrix = matrix(centre, angle);
        this.centre = centre;
        this.angle = angle;
    }

    public static SimpleMatrix matrix(SphericalPoint centre, double angle) {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        return new SimpleMatrix(
                new double[][]{
                        {centre.getX() * centre.getX() * (1 - cosAngle) + cosAngle,
                                centre.getY() * centre.getX() * (1 - cosAngle) - centre.getZ() * sinAngle,
                                centre.getZ() * centre.getX() * (1 - cosAngle) + centre.getY() * sinAngle},
                        {centre.getX() * centre.getY() * (1 - cosAngle) + centre.getZ()*sinAngle,
                                centre.getY() * centre.getY() * (1 - cosAngle) + cosAngle,
                                centre.getY() * centre.getZ() * (1 - cosAngle) - centre.getX()*sinAngle},
                        {centre.getZ() * centre.getX() * (1 - cosAngle) - centre.getY()*sinAngle,
                                centre.getZ() * centre.getY() * (1 - cosAngle) + centre.getX()*sinAngle,
                                centre.getZ() * centre.getZ() * (1 - cosAngle) + cosAngle}
                }
        );
    }
}
