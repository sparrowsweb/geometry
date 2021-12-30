package com.sparrows.geometry.transformation;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.exception.InvalidSphericalTransformation;
import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.maths.Maths;

public class SphericalTransformation {
    protected SimpleMatrix matrix;

    protected SphericalTransformation() {
    }

    public SphericalTransformation(SimpleMatrix matrix) throws InvalidSphericalTransformation {
        if (!Maths.equal(Math.abs(matrix.determinant()),1.0)) {
            throw new InvalidSphericalTransformation();
        }
        this.matrix = matrix;
    }

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public SphericalTransformation inverse() throws TransformationNotInvertible {
        try {
            return new SphericalTransformation(matrix.invert());
        } catch (SingularMatrixException | InvalidSphericalTransformation e) {
            throw new TransformationNotInvertible();
        }
    }

    // compose transformation to form 'this' followed by 't'
    public SphericalTransformation compose(SphericalTransformation t) {
        try {
            return new SphericalTransformation(t.getMatrix().mult(getMatrix()));
        } catch (InvalidSphericalTransformation e) {
            e.printStackTrace();
            return null;
        }
    }
}
