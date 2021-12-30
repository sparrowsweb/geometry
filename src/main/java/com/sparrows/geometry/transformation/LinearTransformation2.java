package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.TransformationNotInvertible;
import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

public class LinearTransformation2 {
    private final SimpleMatrix matrix;

    public LinearTransformation2(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public LinearTransformation2 inverse() throws TransformationNotInvertible {
        try {
            return new LinearTransformation2(matrix.invert());
        } catch (SingularMatrixException e) {
            throw new TransformationNotInvertible();
        }
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
