package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

public class LinearTransformation32 {
    private final SimpleMatrix matrix;

    public LinearTransformation32(SimpleMatrix matrix) {
        this.matrix = matrix;
    }
/*
    // the LT such that the unit vectors are mapped to the given basis
    public static LinearTransformation32 toBasis(Basis3 toBasis) {
        return new LinearTransformation32(toBasisMatrix(toBasis));
    }

    // the LT such that the given basis is mapped to the unit vectors
    public static LinearTransformation32 fromBasis(Basis3 fromBasis) throws InvalidBasisException {
        return new LinearTransformation32(fromBasisMatrix(fromBasis));
    }

    // matrix to convert unit vectors to a given basis
    public static SimpleMatrix toBasisMatrix(Basis3 toBasis) {
        return new SimpleMatrix(
                new double[][]{
                        {toBasis.getXUnit().getX(), toBasis.getYUnit().getX(), toBasis.getZUnit().getX()},
                        {toBasis.getXUnit().getY(), toBasis.getYUnit().getY(), toBasis.getZUnit().getY()},
                        {toBasis.getXUnit().getZ(), toBasis.getYUnit().getZ(), toBasis.getZUnit().getZ()},
                });
    }
    // matrix to convert a given basis to the unit vectors
    public static SimpleMatrix fromBasisMatrix(Basis3 fromBasis) throws InvalidBasisException {
        try {
            return toBasisMatrix(fromBasis).invert();
        } catch (SingularMatrixException e) {
            throw new InvalidBasisException();
        }
    }
*/
    public SimpleMatrix getMatrix() {
        return matrix;
    }
/*
    public Point2 apply(Point2 p) {
        return p.transform(this);
    }

    public void applySelf(Point2 p) {
        p.setXY(new Point2(this.apply(p)));
    }

    public Vector2 apply(Vector2 v) {
        return v.transform(this);
    }

    public void applySelf(Vector2 v) {
        v.setXY(new Vector2(this.apply(v)));
    }*/
}
