package com.sparrows.geometry.transformation.d3;

import com.sparrows.geometry.exception.InvalidBasisException;
import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.transformation.Basis3;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

public class LinearTransformation3 {
    protected final SimpleMatrix matrix;

    public LinearTransformation3(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    // the LT such that the unit vectors are mapped to the given basis
    public static LinearTransformation3 toBasis(Basis3 toBasis) {
        return new LinearTransformation3(toBasisMatrix(toBasis));
    }

    // the LT such that the given basis is mapped to the unit vectors
    public static LinearTransformation3 fromBasis(Basis3 fromBasis) throws InvalidBasisException {
        return new LinearTransformation3(fromBasisMatrix(fromBasis));
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

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public LinearTransformation3 inverse() throws TransformationNotInvertible {
        try {
            return new LinearTransformation3(matrix.invert());
        } catch (SingularMatrixException e) {
            throw new TransformationNotInvertible();
        }
    }

    // compose transformation to form 'this' followed by 'a'
    public LinearTransformation3 compose(LinearTransformation3 t) {
        return new LinearTransformation3(t.getMatrix().mult(getMatrix()));
    }
}
