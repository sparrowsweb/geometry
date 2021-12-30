package com.sparrows.geometry.transformation;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.transformation.d3.LinearTransformation3;

public class Identity3 extends LinearTransformation3 {

    public Identity3() {
        super(matrix());
    }

    public static SimpleMatrix matrix() {
        return SimpleMatrix.identity(3);
    }
}
