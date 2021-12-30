package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Stretch2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkMatrix;

class LinearTransformation2Test {

    @Test
    void TestConstructor() {
        LinearTransformation2 t = new Stretch2(2,3);
        checkMatrix(new double[][]{
                        {2,0},
                        {0,3}},
                t.getMatrix());
    }

    @Test
    void TestInverse() throws TransformationNotInvertible {
        LinearTransformation2 t = new Stretch2(2,5);
        LinearTransformation2 i = t.inverse();
        checkMatrix(new double[][]{
                        {.5,0},
                        {0,.2}},
                i.getMatrix());
    }

    @Test
    void TestInverseSingular() {
        LinearTransformation2 t = new Stretch2(2, 0);
        Assertions.assertThrows(TransformationNotInvertible.class, t::inverse);
    }
}
