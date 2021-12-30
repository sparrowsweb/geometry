package com.sparrows.geometry.transformation.d3;

import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.Translation3;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

public class AffineTransformation3 {
    private final SimpleMatrix matrix;

    public AffineTransformation3(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    public AffineTransformation3(LinearTransformation3 linearTransformation, Translation3 translation) {
        matrix = affineMatrix(linearTransformation, translation);
    }

    public AffineTransformation3(LinearTransformation3 linearTransformation) {
        matrix = affineMatrix(linearTransformation);
    }

    public AffineTransformation3(Translation3 translation) {
        matrix = affineMatrix(translation);
    }


    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public static SimpleMatrix affineMatrix(LinearTransformation3 linearTransformation, Translation3 translation){
        return new SimpleMatrix(new double[][]{
                {linearTransformation.getMatrix().get(0,0),
                        linearTransformation.getMatrix().get(0,1),
                        linearTransformation.getMatrix().get(0,2),
                        translation.getMatrix().get(0,0)},
                {linearTransformation.getMatrix().get(1,0),
                        linearTransformation.getMatrix().get(1,1),
                        linearTransformation.getMatrix().get(1,2),
                        translation.getMatrix().get(1,0)},
                {linearTransformation.getMatrix().get(2,0),
                        linearTransformation.getMatrix().get(2,1),
                        linearTransformation.getMatrix().get(2,2),
                        translation.getMatrix().get(2,0)},
                {0,0,0,1}
        });
    }

    public static SimpleMatrix affineMatrix(LinearTransformation3 linearTransformation){
        return new SimpleMatrix(new double[][]{
                {linearTransformation.getMatrix().get(0,0),
                        linearTransformation.getMatrix().get(0,1),
                        linearTransformation.getMatrix().get(0,2),
                        0},
                {linearTransformation.getMatrix().get(1,0),
                        linearTransformation.getMatrix().get(1,1),
                        linearTransformation.getMatrix().get(1,2),
                        0},
                {linearTransformation.getMatrix().get(2,0),
                        linearTransformation.getMatrix().get(2,1),
                        linearTransformation.getMatrix().get(2,2),
                        0},
                {0,0,0,1}
        });
    }

    public static SimpleMatrix affineMatrix(Translation3 translation){
        return new SimpleMatrix(new double[][]{
                {1, 0, 0, translation.getMatrix().get(0,0)},
                {0, 1, 0, translation.getMatrix().get(1,0)},
                {0, 0, 1, translation.getMatrix().get(2,0)},
                {0,0,0,1}
        });
    }

    public LinearTransformation3 linearTransformation() {
        return new LinearTransformation3(
                new SimpleMatrix(new double[][] {
                        {matrix.get(0,0), matrix.get(0,1), matrix.get(0,2)},
                        {matrix.get(1,0), matrix.get(1,1), matrix.get(1,2)},
                        {matrix.get(2,0), matrix.get(2,1), matrix.get(2,2)}
                }));
    }

    public Translation3 translation() {
        return new Translation3(
                new SimpleMatrix(new double[][] {
                        {matrix.get(0,matrix.numCols()-1)},
                        {matrix.get(1,matrix.numCols()-1)},
                        {matrix.get(2,matrix.numCols()-1)}
                }));
    }

    public boolean identical(AffineTransformation3 a) {
        for (var row = 0; row < 4; row++) {
            for (var col = 0; col < 4; col++) {
                if (!Maths.equal(a.matrix.get(row,col),matrix.get(row,col))) {
                    return false;
                }
            }
        }
        return true;
    }

    public AffineTransformation3 inverse() throws TransformationNotInvertible {
        try {
            return new AffineTransformation3(matrix.invert());
        } catch (SingularMatrixException e) {
            throw new TransformationNotInvertible();
        }
    }

    // compose transformation to form 'this' followed by 'a'
    public AffineTransformation3 compose(AffineTransformation3 a) {
        return new AffineTransformation3(a.getMatrix().mult(getMatrix()));
    }
}
