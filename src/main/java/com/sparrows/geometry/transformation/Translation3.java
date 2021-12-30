package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Vector3;
import org.ejml.simple.SimpleMatrix;

public class Translation3 {
    private final SimpleMatrix matrix;

    public Translation3(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    public Translation3(Vector3 v) {
        this.matrix = new SimpleMatrix(v.matrix());
    }

    public Translation3(double x, double y, double z) {
        this.matrix = new SimpleMatrix( new double[][]{{x},{y},{z}});
    }

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public Translation3 inverse() {
        return new Translation3(matrix.negative());
    }
}
