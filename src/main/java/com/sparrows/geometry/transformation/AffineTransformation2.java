package com.sparrows.geometry.transformation;

import com.sparrows.geometry.exception.TransformationNotInvertible;
import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

public class AffineTransformation2 {
    protected final SimpleMatrix matrix;

    public AffineTransformation2(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    public AffineTransformation2(LinearTransformation2 linearTransformation, Translation2 translation) {
        matrix = affineMatrix(linearTransformation, translation);
    }

    public AffineTransformation2(LinearTransformation2 linearTransformation) {
        matrix = affineMatrix(linearTransformation);
    }

    public AffineTransformation2(Translation2 translation) {
        matrix = affineMatrix(translation);
    }

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public static SimpleMatrix affineMatrix(LinearTransformation2 linearTransformation, Translation2 translation){
        return new SimpleMatrix(new double[][]{
                {linearTransformation.getMatrix().get(0,0), linearTransformation.getMatrix().get(0,1), translation.getMatrix().get(0,0)},
                {linearTransformation.getMatrix().get(1,0), linearTransformation.getMatrix().get(1,1), translation.getMatrix().get(1,0)},
                {0,0,1}
        });
    }

    public static SimpleMatrix affineMatrix(LinearTransformation2 linearTransformation){
        return new SimpleMatrix(new double[][]{
                {linearTransformation.getMatrix().get(0,0), linearTransformation.getMatrix().get(0,1), 0},
                {linearTransformation.getMatrix().get(1,0), linearTransformation.getMatrix().get(1,1), 0},
                {0,0,1}
        });
    }

    public static SimpleMatrix affineMatrix(Translation2 translation){
        return new SimpleMatrix(new double[][]{
                {1, 0, translation.getMatrix().get(0,0)},
                {0, 1, translation.getMatrix().get(1,0)},
                {0,0,1}
        });
    }

    public LinearTransformation2 linearTransformation() {
        return new LinearTransformation2(
                new SimpleMatrix(new double[][] {
                        {matrix.get(0,0), matrix.get(0,1)},
                        {matrix.get(1,0), matrix.get(1,1)}
                }));
    }

    public Translation2 translation() {
        return new Translation2(
                new SimpleMatrix(new double[][] {
                        {matrix.get(0,matrix.numCols()-1)},
                        {matrix.get(1,matrix.numCols()-1)}
                }));
    }

    public AffineTransformation2 inverse() throws TransformationNotInvertible {
        try {
            return new AffineTransformation2(matrix.invert());
        } catch (SingularMatrixException e) {
            throw new TransformationNotInvertible();
        }
    }

    // compose transformation to form 'this' followed by 'a'
    public AffineTransformation2 compose(AffineTransformation2 a) {
        return new AffineTransformation2(a.getMatrix().mult(getMatrix()));
    }
}
