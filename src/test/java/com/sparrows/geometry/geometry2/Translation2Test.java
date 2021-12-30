package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.transformation.Translation2;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkMatrix;

class Translation2Test {

    @Test
    void TestConstructor() {
        Translation2 t = new Translation2(2,3);
        checkMatrix(new double[][]{
                        {2},
                        {3}},
                t.getMatrix());
    }

    @Test
    void TestInverse() {
        Translation2 t = new Translation2(2,3);
        Translation2 i = t.inverse();
        checkMatrix(new double[][]{
                        {-2},
                        {-3}},
                i.getMatrix());
    }
}
