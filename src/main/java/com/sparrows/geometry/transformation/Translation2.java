package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry2.Vector2;
import org.ejml.simple.SimpleMatrix;

public class Translation2 {
    private final SimpleMatrix matrix;

    public Translation2(SimpleMatrix matrix) {
        this.matrix = matrix;
    }

    public Translation2(Vector2 v) {
        this.matrix = new SimpleMatrix(v.matrix());
    }

    public Translation2(double x, double y) {
        this.matrix = new SimpleMatrix( new double[][]{{x},{y}});
    }

    public SimpleMatrix getMatrix() {
        return matrix;
    }

    public Translation2 inverse() {
        return new Translation2(matrix.negative());
    }
}
