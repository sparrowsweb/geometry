package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.InvalidBasisException;
import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.Basis3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Stretch3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkMatrix;
import static com.sparrows.geometry.utils.TestUtils.checkVector3;

class LinearTransformation3Test {

    @Test
    void TestConstructor() {
        LinearTransformation3 t = new Stretch3(2,3,4);
        checkMatrix(new double[][]{
                        {2,0,0},
                        {0,3,0},
                        {0,0,4}},
                t.getMatrix());
    }

    @Test
    void TestInverse() throws TransformationNotInvertible {
        LinearTransformation3 t = new Stretch3(2,5,4);
        LinearTransformation3 i = t.inverse();
        checkMatrix(new double[][]{
                        {.5,0,0},
                        {0,.2,0},
                        {0,0,.25}},
                i.getMatrix());
    }

    @Test
    void TestInverseSingular() {
        LinearTransformation3 t = new Stretch3(2, 0,1);
        Assertions.assertThrows(TransformationNotInvertible.class, t::inverse);
    }

    @Test
    void TestToBasis() {
        Basis3 b = new Basis3(new Vector3(1,1,1),new Vector3(1,-1,1),new Vector3(1,-1,-1));
        LinearTransformation3 l = LinearTransformation3.toBasis(b);
        checkVector3(Vector3.X_UNIT.linearTransform(l),1,1,1);
        checkVector3(Vector3.Y_UNIT.linearTransform(l),1,-1,1);
        checkVector3(Vector3.Z_UNIT.linearTransform(l),1,-1,-1);
    }

    @Test
    void TestFromBasis() throws InvalidBasisException {
        Basis3 b = new Basis3(new Vector3(1,1,1),new Vector3(1,-1,1),new Vector3(1,-1,-1));
        LinearTransformation3 l = LinearTransformation3.fromBasis(b);
        checkVector3(new Vector3(1,1,1).linearTransform(l),1,0,0);
        checkVector3(new Vector3(1,-1,1).linearTransform(l),0,1,0);
        checkVector3(new Vector3(1,-1,-1).linearTransform(l),0,0,1);
    }

    @Test
    void TestFromBasisSingular() throws InvalidBasisException {
        Basis3 b = new Basis3(new Vector3(1,1,1),new Vector3(1,-1,1),new Vector3(2,0,2));
        Assertions.assertThrows(InvalidBasisException.class, () -> {
                    LinearTransformation3.fromBasis(b);
                });
    }

}
