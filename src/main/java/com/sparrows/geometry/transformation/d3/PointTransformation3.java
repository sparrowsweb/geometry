package com.sparrows.geometry.transformation.d3;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.exception.NotAPointSymmetry;
import com.sparrows.geometry.maths.Maths;

/**
 * A point transformation. This is a symmetry that leaves the origin fixed and
 * preserves distance.
 */
public class PointTransformation3 extends LinearTransformation3 {

    public PointTransformation3(SimpleMatrix matrix) throws NotAPointSymmetry {
        super(matrix);
        if (!Maths.equal(Math.abs(matrix.determinant()),1)) {
            throw new NotAPointSymmetry();
        }
    }
}
